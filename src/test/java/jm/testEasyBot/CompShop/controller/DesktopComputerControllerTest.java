package jm.testEasyBot.CompShop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import jm.testEasyBot.CompShop.dto.DesktopComputerDto;
import jm.testEasyBot.CompShop.models.DesktopComputer;
import jm.testEasyBot.CompShop.models.enums.FormFactor;
import jm.testEasyBot.CompShop.repository.DesktopComputerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class DesktopComputerControllerTest {

    @Autowired
    private DesktopComputerRepo desktopComputerRepo;

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    private final DesktopComputer comp = new DesktopComputer();
    private final DesktopComputerDto dto = new DesktopComputerDto();

    @BeforeEach
    void init() {
        comp.setSerialNumber("12345");
        comp.setManufacturer("Example manufacture");
        comp.setPrice(5000);
        comp.setQuantity(3000L);
        comp.setFormFactor(FormFactor.DESKTOP);
        desktopComputerRepo.save(comp);
    }

    @Test
    public void addNewProduct() throws Exception {
        dto.setSerialNumber("12345");
        dto.setManufacturer("Example manufacture 2");
        dto.setPrice(1000);
        dto.setQuantity(2000L);
        dto.setFormFactor(FormFactor.NETTOP);

        mvc.perform(post("/api/v1/product/desktop")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isOk());
    }

}