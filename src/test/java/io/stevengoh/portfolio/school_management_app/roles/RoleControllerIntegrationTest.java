package io.stevengoh.portfolio.school_management_app.roles;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.stevengoh.portfolio.school_management_app.common.dtos.BaseResponse;
import io.stevengoh.portfolio.school_management_app.common.enums.ActorType;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.request.CreateRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.request.CreateRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.request.UpdateRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.roles.dtos.response.DetailedResRoleDto;
import io.stevengoh.portfolio.school_management_app.modules.users.UserRepository;
import io.stevengoh.portfolio.school_management_app.modules.users.entities.User;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RoleControllerIntegrationTest {
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "update");
    }

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    static UUID userId;

    @BeforeAll
    static void setupUser(@Autowired UserRepository userRepository, @Autowired PasswordEncoder passwordEncoder) {
        if (userRepository.findByUsername("testuser").isEmpty()) {
            User user = new User();
            user.setUsername("john");
            user.setPassword("secret");
            user.setEmail("testuser@example.com");
            // set other required fields
            userRepository.save(user);
        }
    }

    @Test
    @Order(1)
    @WithMockUser
    void testCreateRole() throws Exception {
        CreateRoleDto dto = new CreateRoleDto();
        dto.setName("staff"); // adapt as needed
        dto.setActorType(ActorType.STAFF);

        MvcResult res = mockMvc.perform(post("/roles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk()).andReturn();

        String json = res.getResponse().getContentAsString();
        userId = UUID.fromString(objectMapper.readTree(json).path("data").path("uuid").asText());
    }

    @Test
    @Order(2)
    @WithMockUser
    void testGetRole() throws Exception {
        mockMvc.perform(get("/roles/{uuid}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.uuid").value(userId.toString()));
    }

    @Test
    @Order(3)
    @WithMockUser
    void testUpdateRole() throws Exception {
        UpdateRoleDto dto = new UpdateRoleDto();
        dto.setName("staff edit");

        mockMvc.perform(put("/roles/{uuid}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("staff edit"));
    }

    @Test
    @Order(4)
    @WithMockUser
    void testGetRolesPagination() throws Exception {
        mockMvc.perform(get("/roles?page=0&size=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.content").isArray());
    }

    @Test
    @Order(5)
//    @WithMockUser(username = "steven")
    @WithUserDetails(value = "john", userDetailsServiceBeanName = "userDetailsServiceImpl")
    void testDeleteRole() throws Exception {
        mockMvc.perform(delete("/roles/{uuid}", userId))
                .andExpect(status().isOk());

        mockMvc.perform(get("/roles/{uuid}", userId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode").value("NOT_FOUND"));
    }

    @Test
    @Order(6)
    @WithUserDetails(value = "john", userDetailsServiceBeanName = "userDetailsServiceImpl")
    void shouldThrowAfterRoleIsDeleted() throws Exception {
        // 1. Create institution
        CreateRoleDto createDto = new CreateRoleDto();
        createDto.setName("staff");
        createDto.setActorType(ActorType.STAFF);

        String createResponse = mockMvc.perform(post("/roles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDto)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        BaseResponse<DetailedResRoleDto> created = objectMapper.readValue(
                createResponse,
                new TypeReference<>() {
                }
        );
        UUID uuid = created.getData().getUuid();

        // 2. Delete institution
        mockMvc.perform(delete("/roles/{uuid}", uuid))
                .andExpect(status().isOk());

        // 3. Get institution (should return data=null or not found depending on your logic)
        mockMvc.perform(get("/roles/{uuid}", uuid))
                .andExpect(status().isNotFound());
    }
}