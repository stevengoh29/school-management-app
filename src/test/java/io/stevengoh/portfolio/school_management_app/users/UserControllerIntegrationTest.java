package io.stevengoh.portfolio.school_management_app.users;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.stevengoh.portfolio.school_management_app.common.dtos.BaseResponse;
import io.stevengoh.portfolio.school_management_app.modules.users.dtos.request.CreateUserDto;
import io.stevengoh.portfolio.school_management_app.modules.users.dtos.request.UpdateUserDto;
import io.stevengoh.portfolio.school_management_app.modules.users.dtos.response.DetailedResUserDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
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
class UserControllerIntegrationTest {

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

    @Test
    @Order(1)
    @WithMockUser
    void testCreateUser() throws Exception {
        CreateUserDto dto = new CreateUserDto();
        dto.setUsername("john_doe"); // adapt as needed

        MvcResult res = mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk()).andReturn();

        String json = res.getResponse().getContentAsString();
        userId = UUID.fromString(objectMapper.readTree(json).path("data").path("uuid").asText());
    }

    @Test
    @Order(2)
    @WithMockUser
    void testGetUser() throws Exception {
        mockMvc.perform(get("/users/{uuid}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.uuid").value(userId.toString()));
    }

    @Test
    @Order(3)
    @WithMockUser
    void testUpdateUser() throws Exception {
        UpdateUserDto dto = new UpdateUserDto();
        dto.setUsername("john_wick");

        mockMvc.perform(put("/users/{uuid}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.username").value("john_wick"));
    }

    @Test
    @Order(4)
    @WithMockUser
    void testGetUsersPagination() throws Exception {
        mockMvc.perform(get("/users?page=0&size=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.content").isArray());
    }

    @Test
    @Order(5)
    @WithMockUser
    void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/users/{uuid}", userId))
                .andExpect(status().isOk());

        mockMvc.perform(get("/users/{uuid}", userId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode").value("NOT_FOUND"));
    }

    @Test
    @Order(6)
    void shouldThrowAfterUserIsDeleted() throws Exception {
        // 1. Create institution
        CreateUserDto createDto = new CreateUserDto();
        createDto.setUsername("john_wok");

        String createResponse = mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDto)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        BaseResponse<DetailedResUserDto> created = objectMapper.readValue(
                createResponse,
                new TypeReference<>() {
                }
        );
        UUID uuid = created.getData().getUuid();

        // 2. Delete institution
        mockMvc.perform(delete("/users/{uuid}", uuid))
                .andExpect(status().isOk());

        // 3. Get institution (should return data=null or not found depending on your logic)
        mockMvc.perform(get("/users/{uuid}", uuid))
                .andExpect(status().isNotFound());
    }
}

