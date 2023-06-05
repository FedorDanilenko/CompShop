package jm.testEasyBot.CompShop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jm.testEasyBot.CompShop.dto.LaptopDto;
import jm.testEasyBot.CompShop.service.impl.LaptopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product/laptop")
@Tag(name = "Laptops")
public class LaptopController {

    private final LaptopService laptopService;

    @Operation(summary = "Add new Laptop")
    @PostMapping
    public ResponseEntity<?> addLaptop(@RequestBody LaptopDto dto) {
        return ResponseEntity.ok(laptopService.addNewProduct(dto));
    }

    @Operation(summary = "Update product info")
    @PutMapping("/{serNum}")
    private ResponseEntity<?> updateLaptop(@PathVariable("serNum") String serNum,
                                            @RequestBody LaptopDto dto) {
        return ResponseEntity.ok(laptopService.updateProduct(serNum, dto));
    }

    @Operation(summary = "Get product by type and serial number")
    @GetMapping("/{serNum}")
    public ResponseEntity<?> getLaptopById(@PathVariable("serNum") String serNum) {
        return ResponseEntity.ok(laptopService.getProduct(serNum));
    }

    @Operation(summary = "Get list all product by type")
    @GetMapping
    public ResponseEntity<?> getAllLaptop() {
        return ResponseEntity.ok(laptopService.getAllProducts());
    }
}
