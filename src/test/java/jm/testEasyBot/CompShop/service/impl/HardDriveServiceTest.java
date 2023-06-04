package jm.testEasyBot.CompShop.service.impl;

import jm.testEasyBot.CompShop.dto.HardDriveDto;
import jm.testEasyBot.CompShop.dto.MonitorDto;
import jm.testEasyBot.CompShop.models.HardDrive;
import jm.testEasyBot.CompShop.models.Monitor;
import jm.testEasyBot.CompShop.repository.HardDriveRepo;
import jm.testEasyBot.CompShop.repository.MonitorRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HardDriveServiceTest {

    @Autowired
    private HardDriveRepo hardDriveRepo;
    @Autowired
    private HardDriveService hardDriveService;

    @Test
    void addNewMonitor() {
        HardDriveDto dto = new HardDriveDto();
        dto.setSerialNumber("123456789");
        dto.setManufacturer("Example Manufacturer");
        dto.setPrice(1000);
        dto.setQuantity(10L);
        dto.setCapacity(15);

        hardDriveService.addNewProduct(dto);

        List<HardDrive> hardDrives = hardDriveRepo.findAll();
        HardDrive hardDrive = hardDrives.get(0);

        assertEquals(1, hardDrives.size());
        assertEquals(15, hardDrive.getCapacity());
        assertEquals("123456789", hardDrive.getSerialNumber());

    }
}