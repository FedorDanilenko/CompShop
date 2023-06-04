package jm.testEasyBot.CompShop.service.impl;

import jm.testEasyBot.CompShop.dto.DesktopComputerDto;
import jm.testEasyBot.CompShop.dto.MonitorDto;
import jm.testEasyBot.CompShop.models.DesktopComputer;
import jm.testEasyBot.CompShop.models.Monitor;
import jm.testEasyBot.CompShop.models.enums.FormFactor;
import jm.testEasyBot.CompShop.repository.DesktopComputerRepo;
import jm.testEasyBot.CompShop.repository.MonitorRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MonitorServiceTest {

    @Autowired
    private MonitorRepo MonitorRepo;
    @Autowired
    private MonitorService monitorService;

    @Test
    void addNewMonitor() {
        MonitorDto dto = new MonitorDto();
        dto.setSerialNumber("123456789");
        dto.setManufacturer("Example Manufacturer");
        dto.setPrice(1000);
        dto.setQuantity(10L);
        dto.setDiagonalSize(15);

        monitorService.addNewProduct(dto);

        List<Monitor> monitors = MonitorRepo.findAll();
        Monitor monitor = monitors.get(0);

        assertEquals(1, monitors.size());
        assertEquals(15, monitor.getDiagonalSize());
        assertEquals("123456789", monitor.getSerialNumber());

    }
}