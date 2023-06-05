package jm.testEasyBot.CompShop.service.impl;

import jm.testEasyBot.CompShop.dto.HardDriveDto;
import jm.testEasyBot.CompShop.exeprion.AlreadyExistsException;
import jm.testEasyBot.CompShop.exeprion.NotFoundException;
import jm.testEasyBot.CompShop.models.HardDrive;
import jm.testEasyBot.CompShop.repository.HardDriveRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class HardDriveServiceTest {

    @Autowired
    private HardDriveService hardDriveService;

    @MockBean
    private HardDriveRepo hardDriveRepo;

    private final HardDrive hardDrive = new HardDrive();

    private final HardDriveDto dto = new HardDriveDto();

    @BeforeEach
    void start() {
        hardDrive.setSerialNumber("12345");
        hardDrive.setManufacturer("Example manufacture");
        hardDrive.setPrice(1500);
        hardDrive.setQuantity(100L);
        hardDrive.setCapacity(512);

        dto.setSerialNumber("54321");
        dto.setManufacturer("Example manufacture 2");
        dto.setPrice(2000);
        dto.setQuantity(85L);
        dto.setCapacity(512);
    }

    @Test
    void getAllProducts_ShouldReturnListOfProducts() {
        HardDrive drive = new HardDrive();
        drive.setSerialNumber("54321");
        drive.setManufacturer("Example manufacture 2");
        drive.setPrice(2000);
        drive.setQuantity(85L);
        drive.setCapacity(1024);

        // Arrange
        List<HardDrive> products = Arrays.asList(
            hardDrive, drive
        );
        when(hardDriveRepo.findAll()).thenReturn(products);

        // Act
        List<HardDriveDto> result = hardDriveService.getAllProducts();

        // Assert
        assertThat(result).hasSize(2);
        assertEquals("12345", result.get(0).getSerialNumber());
        assertEquals(512, result.get(0).getCapacity());
        assertEquals("54321", result.get(1).getSerialNumber());
        assertEquals(1024, result.get(1).getCapacity());
    }

    @Test
    void getProduct_ExistingProduct_ShouldReturnProductDto() {
        // Arrange
        when(hardDriveRepo.findBySerialNumber("12345")).thenReturn(hardDrive);

        // Act
        HardDriveDto result = hardDriveService.getProduct("12345");

        // Assert
        assertEquals("12345", result.getSerialNumber());
        assertEquals(512, result.getCapacity());
    }

    @Test
    void getProduct_NonExistingProduct_ShouldThrowNotFoundException() {
        // Arrange
        when(hardDriveRepo.findBySerialNumber("2")).thenReturn(null);

        // Act & Assert
        assertThrows(NotFoundException.class, () -> hardDriveService.getProduct("2"));
    }

    @Test
    void addNewProduct_ValidProduct_ShouldReturnAddedProductDto() {
        // Arrange
        when(hardDriveRepo.save(any())).thenReturn(hardDrive);

        // Act
        HardDriveDto result = hardDriveService.addNewProduct(dto);

        // Assert
        assertEquals("12345", result.getSerialNumber());
        assertEquals(512, result.getCapacity());
    }

    @Test
    void addNewProduct_DuplicateSerialNumber_ShouldThrowAlreadyExistsException() {
        // Arrange
        when(hardDriveRepo.save(any())).thenThrow(AlreadyExistsException.class);

        // Act & Assert
        assertThrows(AlreadyExistsException.class, () -> hardDriveService.addNewProduct(dto));
    }

    @Test
    void updateProduct_ExistingProduct_ShouldReturnUpdatedProductDto() {
        // Arrange
        dto.setSerialNumber("12345");
        dto.setCapacity(24);
        when(hardDriveRepo.findBySerialNumber("12345")).thenReturn(hardDrive);
        when(hardDriveRepo.save(any())).thenReturn(hardDrive);

        // Act
        HardDriveDto result = hardDriveService.updateProduct("12345", dto);

        // Assert
        assertEquals("12345", result.getSerialNumber());
        assertEquals(24, result.getCapacity());
    }

    @Test
    void updateProduct_NonExistingProduct_ShouldThrowNotFoundException() {
        // Arrange
        when(hardDriveRepo.findBySerialNumber("2")).thenThrow(NotFoundException.class);

        // Act & Assert
        assertThrows(NotFoundException.class, () -> hardDriveService.updateProduct("2", dto));
    }

    @Test
    void updateProduct_DuplicateSerialNumber_ShouldThrowAlreadyExistsException() {
        // Arrange
        dto.setCapacity(512);
        when(hardDriveRepo.findBySerialNumber("12345")).thenReturn(hardDrive);
        when(hardDriveRepo.save(any())).thenThrow(AlreadyExistsException.class);

        // Act & Assert
        assertThrows(AlreadyExistsException.class, () -> hardDriveService.updateProduct("12345", dto));
    }
}
