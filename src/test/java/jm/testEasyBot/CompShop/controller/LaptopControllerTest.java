package jm.testEasyBot.CompShop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jm.testEasyBot.CompShop.dto.LaptopDto;
import jm.testEasyBot.CompShop.models.Laptop;
import jm.testEasyBot.CompShop.models.enums.LaptopSize;
import jm.testEasyBot.CompShop.repository.LaptopRepo;
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
class LaptopControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private LaptopRepo repo;

    private final Laptop product = new Laptop();
    private final LaptopDto dto = new LaptopDto();

    @BeforeEach
    void init() {
        product.setSerialNumber("12345");
        product.setManufacturer("Example manufacture 2");
        product.setPrice(1000);
        product.setQuantity(150L);
        product.setSize(LaptopSize.INCHES_13);
        repo.save(product);

        dto.setSerialNumber("54321");
        dto.setManufacturer("Example manufacture 2");
        dto.setPrice(2000);
        dto.setQuantity(85L);
        dto.setSize(LaptopSize.INCHES_17);
    }

    @AfterEach
    void clear() {
        repo.deleteAll();
    }
    @Test
    public void addProduct_ValidDto_ReturnsOkResponse() throws Exception {

        // Act
        mockMvc.perform(post("/api/v1/product/laptop")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.serialNumber").value(dto.getSerialNumber()));
    }

    @Test
    public void updateProduct_ValidDto_ReturnsOkResponse() throws Exception {
        // Arrange
        String serialNumber = "12345";
        dto.setSize(LaptopSize.INCHES_15);


        // Act
        mockMvc.perform(put("/api/v1/product/laptop/{serNum}", serialNumber)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size").value(dto.getSize().toString()));
    }

    @Test
    public void getProductById_ExistingSerialNumber_ReturnsOkResponse() throws Exception {
        // Arrange
        String serialNumber = "12345";

        // Act
        mockMvc.perform(get("/api/v1/product/laptop/{serNum}", serialNumber))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.serialNumber").value(product.getSerialNumber()));
    }

    @Test
    public void getAllProducts_ReturnsOkResponse() throws Exception {
        // Arrange
        List<LaptopDto> dtos = List.of(dto);

        // Act
        mockMvc.perform(get("/api/v1/product/laptop"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$", hasSize(dtos.size())));
    }

    private static String asJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}