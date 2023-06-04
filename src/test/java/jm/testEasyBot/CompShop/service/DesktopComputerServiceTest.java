package jm.testEasyBot.CompShop.service;

import jm.testEasyBot.CompShop.dto.DesktopComputerDto;
import jm.testEasyBot.CompShop.models.DesktopComputer;
import jm.testEasyBot.CompShop.models.enums.FromFactor;
import jm.testEasyBot.CompShop.repository.DesktopComputerRepo;
import jm.testEasyBot.CompShop.service.impl.DesktopComputerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
class DesktopComputerServiceTest {

    @Autowired
    private DesktopComputerRepo desktopComputerRepo;
    @Autowired
    private DesktopComputerService desktopComputerService;

    @Test
    void addNewComp() {
        DesktopComputerDto dto = new DesktopComputerDto();
        dto.setSerialNumber("123456789");
        dto.setManufacturer("Example Manufacturer");
        dto.setPrice(1000);
        dto.setQuantity(10L);
        dto.setFromFactor(FromFactor.DESKTOP);

        desktopComputerService.addNewProduct(dto);

        List<DesktopComputer> desktopComputers = desktopComputerRepo.findAll();
        DesktopComputer desktopComputer = desktopComputers.get(0);

        assertEquals(1, desktopComputers.size());
        assertEquals("DESKTOP", desktopComputer.getFromFactor().toString());
        assertEquals("123456789", desktopComputer.getSerialNumber());

    }
}