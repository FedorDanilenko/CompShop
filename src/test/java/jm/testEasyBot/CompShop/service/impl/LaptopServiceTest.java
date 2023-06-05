package jm.testEasyBot.CompShop.service.impl;

import jm.testEasyBot.CompShop.dto.LaptopDto;
import jm.testEasyBot.CompShop.exeprion.AlreadyExistsException;
import jm.testEasyBot.CompShop.exeprion.NotFoundException;
import jm.testEasyBot.CompShop.models.Laptop;
import jm.testEasyBot.CompShop.models.enums.LaptopSize;
import jm.testEasyBot.CompShop.repository.LaptopRepo;
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
class LaptopServiceTest {

    @Autowired
    private LaptopService service;

    @MockBean
    private LaptopRepo repo;

    private final Laptop product = new Laptop();

    private final LaptopDto dto = new LaptopDto();

    @BeforeEach
    void start() {
        product.setSerialNumber("12345");
        product.setManufacturer("Example manufacture");
        product.setPrice(1500);
        product.setQuantity(100L);
        product.setSize(LaptopSize.INCHES_13);

        dto.setSerialNumber("54321");
        dto.setManufacturer("Example manufacture 2");
        dto.setPrice(2000);
        dto.setQuantity(85L);
        dto.setSize(LaptopSize.INCHES_15);
    }

    @Test
    void getAllProducts_ShouldReturnListOfProducts() {
        Laptop newProduct = new Laptop();
        newProduct.setSerialNumber("54321");
        newProduct.setManufacturer("Example manufacture 2");
        newProduct.setPrice(2000);
        newProduct.setQuantity(85L);
        newProduct.setSize(LaptopSize.INCHES_15);

        // Arrange
        List<Laptop> products = Arrays.asList(
            product, newProduct
        );
        when(repo.findAll()).thenReturn(products);

        // Act
        List<LaptopDto> result = service.getAllProducts();

        // Assert
        assertThat(result).hasSize(2);
        assertEquals("12345", result.get(0).getSerialNumber());
        assertEquals(LaptopSize.INCHES_13, result.get(0).getSize());
        assertEquals("54321", result.get(1).getSerialNumber());
        assertEquals(LaptopSize.INCHES_15, result.get(1).getSize());
    }

    @Test
    void getProduct_ExistingProduct_ShouldReturnProductDto() {
        // Arrange
        when(repo.findBySerialNumber("12345")).thenReturn(product);

        // Act
        LaptopDto result = service.getProduct("12345");

        // Assert
        assertEquals("12345", result.getSerialNumber());
        assertEquals(LaptopSize.INCHES_13, result.getSize());
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
        LaptopDto result = service.addNewProduct(dto);

        // Assert
        assertEquals("12345", result.getSerialNumber());
        assertEquals(LaptopSize.INCHES_13, result.getSize());
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
        dto.setSize(LaptopSize.INCHES_17);
        when(repo.findBySerialNumber("12345")).thenReturn(product);
        when(repo.save(any())).thenReturn(product);

        // Act
        LaptopDto result = service.updateProduct("12345", dto);

        // Assert
        assertEquals("12345", result.getSerialNumber());
        assertEquals(LaptopSize.INCHES_17, result.getSize());
    }

    @Test
    void updateProduct_NonExistingProduct_ShouldThrowNotFoundException() {
        // Arrange
        when(repo.findBySerialNumber("54321")).thenThrow(NotFoundException.class);

        // Act & Assert
        assertThrows(NotFoundException.class, () -> service.updateProduct("54321", dto));
    }

    @Test
    void updateProduct_DuplicateSerialNumber_ShouldThrowAlreadyExistsException() {
        // Arrange
        dto.setSize(LaptopSize.INCHES_17);
        when(repo.findBySerialNumber("12345")).thenReturn(product);
        when(repo.save(any())).thenThrow(AlreadyExistsException.class);

        // Act & Assert
        assertThrows(AlreadyExistsException.class, () -> service.updateProduct("12345", dto));
    }

}