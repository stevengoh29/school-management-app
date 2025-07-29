package io.stevengoh.portfolio.school_management_app.institutions;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.stevengoh.portfolio.school_management_app.common.dtos.BaseResponse;
import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.request.CreateInstitutionDto;
import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.request.UpdateInstitutionDto;
import io.stevengoh.portfolio.school_management_app.modules.institutions.dtos.response.DetailedResInstitutionDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InstitutionControllerIntegrationTest {
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

    private static UUID institutionUuid;

    @Test
    @Order(1)
    void shouldCreateInstitution() throws Exception {
        CreateInstitutionDto createDto = new CreateInstitutionDto();
        createDto.setName("Test Institution");
        createDto.setAddress("Jl. Testing No.1");

        MvcResult result = mockMvc.perform(post("/institutions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDto)))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        BaseResponse<?> response = objectMapper.readValue(content, BaseResponse.class);
        Map data = (Map) response.getData();
        institutionUuid = UUID.fromString((String) data.get("uuid"));
        assertNotNull(institutionUuid);
    }

    @Test
    @Order(2)
    void shouldGetInstitutionById() throws Exception {
        mockMvc.perform(get("/institutions/{uuid}", institutionUuid))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("Test Institution"));
    }

    @Test
    @Order(4)
    void shouldDeleteInstitution() throws Exception {
        mockMvc.perform(delete("/institutions/{uuid}", institutionUuid))
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    void shouldUpdateInstitution() throws Exception {
        UpdateInstitutionDto updateDto = new UpdateInstitutionDto();
        updateDto.setName("Updated Name");
        updateDto.setAddress("Jl. Baru No. 2");

        mockMvc.perform(put("/institutions/{uuid}", institutionUuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("Updated Name"));
    }

    @Test
    @Order(5)
    void shouldListInstitutionsWithPagination() throws Exception {
        mockMvc.perform(get("/institutions")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.content").isArray());
    }

    @Test
    void shouldReturn404WhenInstitutionNotFound() throws Exception {
        UUID fakeUuid = UUID.randomUUID();

        mockMvc.perform(get("/institutions/{uuid}", fakeUuid))
                .andExpect(status().isNotFound()) // Still 200 but null body
                .andExpect(jsonPath("$.errorCode").value("NOT_FOUND"));
    }

    @Test
    void shouldReturnNullAfterInstitutionIsDeleted() throws Exception {
        // 1. Create institution
        CreateInstitutionDto createDto = new CreateInstitutionDto();
        createDto.setName("Soft Delete Institution");
        createDto.setCode("SOFT-DEL-001");

        String createResponse = mockMvc.perform(post("/institutions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDto)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        BaseResponse<DetailedResInstitutionDto> created = objectMapper.readValue(
                createResponse,
                new TypeReference<>() {
                }
        );
        UUID uuid = created.getData().getUuid();

        // 2. Delete institution
        mockMvc.perform(delete("/institutions/{uuid}", uuid))
                .andExpect(status().isOk());

        // 3. Get institution (should return data=null or not found depending on your logic)
        mockMvc.perform(get("/institutions/{uuid}", uuid))
                .andExpect(status().isNotFound()) // assuming it returns 200 with data=null
                .andExpect(jsonPath("$.errorCode").value("NOT_FOUND"));
    }

}
