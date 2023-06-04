package jm.testEasyBot.CompShop.service.impl;

import jm.testEasyBot.CompShop.dto.LaptopDto;
import jm.testEasyBot.CompShop.dto.MonitorDto;
import jm.testEasyBot.CompShop.models.Laptop;
import jm.testEasyBot.CompShop.models.Monitor;
import jm.testEasyBot.CompShop.models.enums.LaptopSize;
import jm.testEasyBot.CompShop.repository.LaptopRepo;
import jm.testEasyBot.CompShop.repository.MonitorRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class LaptopServiceTest {

    @Autowired
    private LaptopRepo laptopRepo;
    @Autowired
    private LaptopService laptopService;

    @Test
    void addNewMonitor() {
        LaptopDto dto = new LaptopDto();
        dto.setSerialNumber("123456789");
        dto.setManufacturer("Example Manufacturer");
        dto.setPrice(1000);
        dto.setQuantity(10L);
        dto.setSize(LaptopSize.INCHES_13);

        laptopService.addNewProduct(dto);

        List<Laptop> laptops = laptopRepo.findAll();
        Laptop laptop = laptops.get(0);

        assertEquals(1, laptops.size());
        assertEquals("INCHES_13", laptop.getSize().toString());
        assertEquals("123456789", laptop.getSerialNumber());

    }
}