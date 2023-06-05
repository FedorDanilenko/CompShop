package jm.testEasyBot.CompShop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jm.testEasyBot.CompShop.dto.HardDriveDto;
import jm.testEasyBot.CompShop.models.HardDrive;
import jm.testEasyBot.CompShop.repository.HardDriveRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HardDriveControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private HardDriveRepo repo;

    private final HardDrive product = new HardDrive();
    private final HardDriveDto dto = new HardDriveDto();

    @BeforeEach
    void init() {
        product.setSerialNumber("12345");
        product.setManufacturer("Example manufacture 2");
        product.setPrice(1000);
        product.setQuantity(150L);
        product.setCapacity(512);
        repo.save(product);

        dto.setSerialNumber("54321");
        dto.setManufacturer("Example manufacture 2");
        dto.setPrice(2000);
        dto.setQuantity(85L);
        dto.setCapacity(1024);
    }

    @AfterEach
    void clear() {
        repo.deleteAll();
    }
    @Test
    public void addProduct_ValidDto_ReturnsOkResponse() throws Exception {

        // Act
        mockMvc.perform(post("/api/v1/product/hard-drive")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.serialNumber").value(dto.getSerialNumber()));
    }

    @Test
    public void updateProduct_ValidDto_ReturnsOkResponse() throws Exception {
        // Arrange
        String serialNumber = "12345";
        dto.setCapacity(1024);


        // Act
        mockMvc.perform(put("/api/v1/product/hard-drive/{serNum}", serialNumber)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.capacity").value(dto.getCapacity()));
    }

    @Test
    public void getProductById_ExistingSerialNumber_ReturnsOkResponse() throws Exception {
        // Arrange
        String serialNumber = "12345";

        // Act
        mockMvc.perform(get("/api/v1/product/hard-drive/{serNum}", serialNumber))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.serialNumber").value(product.getSerialNumber()));
    }

    @Test
    public void getAllProducts_ReturnsOkResponse() throws Exception {
        // Arrange
        List<HardDriveDto> dtos = List.of(dto);

        // Act
        mockMvc.perform(get("/api/v1/product/hard-drive"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$", hasSize(dtos.size())));
    }

    private static String asJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}