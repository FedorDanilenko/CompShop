package jm.testEasyBot.CompShop.service.impl;

import jm.testEasyBot.CompShop.dto.DesktopComputerDto;
import jm.testEasyBot.CompShop.exeprion.AlreadyExistsException;
import jm.testEasyBot.CompShop.exeprion.NotFoundException;
import jm.testEasyBot.CompShop.models.DesktopComputer;
import jm.testEasyBot.CompShop.models.enums.FormFactor;
import jm.testEasyBot.CompShop.repository.DesktopComputerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class DesktopComputerServiceTests {

    @Autowired
    private DesktopComputerService service;

    @MockBean
    private DesktopComputerRepo repo;

    private final DesktopComputer product = new DesktopComputer();

    private final DesktopComputerDto dto = new DesktopComputerDto();

    @BeforeEach
    void start() {
        product.setSerialNumber("12345");
        product.setManufacturer("Example manufacture");
        product.setPrice(1500);
        product.setQuantity(100L);
        product.setFormFactor(FormFactor.DESKTOP);

        dto.setSerialNumber("54321");
        dto.setManufacturer("Example manufacture 2");
        dto.setPrice(2000);
        dto.setQuantity(85L);
        dto.setFormFactor(FormFactor.ALL_IN_ONE);
    }

    @Test
    void getAllProducts_ShouldReturnListOfProducts() {
        DesktopComputer newProduct = new DesktopComputer();
        newProduct.setSerialNumber("54321");
        newProduct.setManufacturer("Example manufacture 2");
        newProduct.setPrice(2000);
        newProduct.setQuantity(85L);
        newProduct.setFormFactor(FormFactor.ALL_IN_ONE);

        // Arrange
        List<DesktopComputer> products = Arrays.asList(
            product, newProduct
        );
        when(repo.findAll()).thenReturn(products);

        // Act
        List<DesktopComputerDto> result = service.getAllProducts();

        // Assert
        assertThat(result).hasSize(2);
        assertEquals("12345", result.get(0).getSerialNumber());
        assertEquals(FormFactor.DESKTOP, result.get(0).getFormFactor());
        assertEquals("54321", result.get(1).getSerialNumber());
        assertEquals(FormFactor.ALL_IN_ONE, result.get(1).getFormFactor());
    }

    @Test
    void getProduct_ExistingProduct_ShouldReturnProductDto() {
        // Arrange
        when(repo.findBySerialNumber("12345")).thenReturn(product);

        // Act
        DesktopComputerDto result = service.getProduct("12345");

        // Assert
        assertEquals("12345", result.getSerialNumber());
        assertEquals(FormFactor.DESKTOP, result.getFormFactor());
    }

    @Test
    void getProduct_NonExistingProduct_ShouldThrowNotFoundException() {
        // Arrange
        when(repo.findBySerialNumber("2")).thenReturn(null);

        // Act & Assert
        assertThrows(NotFoundException.class, () -> service.getProduct("2"));
    }

    @Test
    void addNewProduct_ValidProduct_ShouldReturnAddedProductDto() {
        // Arrange
        when(repo.save(any())).thenReturn(product);

        // Act
        DesktopComputerDto result = service.addNewProduct(dto);

        // Assert
        assertEquals("12345", result.getSerialNumber());
        assertEquals(FormFactor.DESKTOP, result.getFormFactor());
    }

    @Test
    void addNewProduct_DuplicateSerialNumber_ShouldThrowAlreadyExistsException() {
        // Arrange
        when(repo.save(any())).thenThrow(AlreadyExistsException.class);

        // Act & Assert
        assertThrows(AlreadyExistsException.class, () -> service.addNewProduct(dto));
    }

    @Test
    void updateProduct_ExistingProduct_ShouldReturnUpdatedProductDto() {
        // Arrange
        dto.setSerialNumber("12345");
        dto.setFormFactor(FormFactor.NETTOP);
        when(repo.findBySerialNumber("12345")).thenReturn(product);
        when(repo.save(any())).thenReturn(product);

        // Act
        DesktopComputerDto result = service.updateProduct("12345", dto);

        // Assert
        assertEquals("12345", result.getSerialNumber());
        assertEquals(FormFactor.NETTOP, result.getFormFactor());
    }

    @Test
    void updateProduct_NonExistingProduct_ShouldThrowNotFoundException() {
        // Arrange
        when(repo.findBySerialNumber("2")).thenThrow(NotFoundException.class);

        // Act & Assert
        assertThrows(NotFoundException.class, () -> service.updateProduct("2", dto));
    }

    @Test
    void updateProduct_DuplicateSerialNumber_ShouldThrowAlreadyExistsException() {
        // Arrange
        dto.setFormFactor(FormFactor.NETTOP);
        when(repo.findBySerialNumber("12345")).thenReturn(product);
        when(repo.save(any())).thenThrow(AlreadyExistsException.class);

        // Act & Assert
        assertThrows(AlreadyExistsException.class, () -> service.updateProduct("12345", dto));
    }
}
