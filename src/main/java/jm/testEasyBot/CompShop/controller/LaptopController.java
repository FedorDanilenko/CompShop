package jm.testEasyBot.CompShop.controller;

import jm.testEasyBot.CompShop.dto.LaptopDto;
import jm.testEasyBot.CompShop.service.impl.LaptopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product/laptop")
public class LaptopController {

    private final LaptopService laptopService;

    @PostMapping
    public ResponseEntity<?> addLaptop(@RequestBody LaptopDto dto) {
        return ResponseEntity.ok(laptopService.addNewProduct(dto));
    }

    @PatchMapping("/{serNum}")
    private ResponseEntity<?> updateLaptop(@PathVariable String serNum,
                                            @RequestBody LaptopDto dto) {
        return ResponseEntity.ok(laptopService.updateProduct(serNum, dto));
    }

    @GetMapping("/{serNum}")
    public ResponseEntity<?> getLaptopById(@PathVariable String serNum) {
        return ResponseEntity.ok(laptopService.getProduct(serNum));
    }

    @GetMapping
    public ResponseEntity<?> getAllLaptop() {
        return ResponseEntity.ok(laptopService.getAllProducts());
    }
}
