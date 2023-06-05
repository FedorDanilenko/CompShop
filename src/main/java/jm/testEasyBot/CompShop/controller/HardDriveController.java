package jm.testEasyBot.CompShop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jm.testEasyBot.CompShop.dto.HardDriveDto;
import jm.testEasyBot.CompShop.service.impl.HardDriveService;
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
@RequestMapping("/api/v1/product/hard-drive")
@Tag(name = "Hard drives")
public class HardDriveController {

    private final HardDriveService hardDriveService;

    @Operation(summary = "Add new hard drive")
    @PostMapping
    public ResponseEntity<?> addHardDrive(@RequestBody HardDriveDto dto) {
        return ResponseEntity.ok(hardDriveService.addNewProduct(dto));
    }

    @Operation(summary = "Update product info")
    @PutMapping("/{serNum}")
    private ResponseEntity<?> updateHardDrive(@PathVariable("serNum") String serNum,
                                           @RequestBody HardDriveDto dto) {
        return ResponseEntity.ok(hardDriveService.updateProduct(serNum, dto));
    }

    @Operation(summary = "Get product by type and serial number")
    @GetMapping("/{serNum}")
    public ResponseEntity<?> getHardDriveById(@PathVariable("serNum") String serNum) {
        return ResponseEntity.ok(hardDriveService.getProduct(serNum));
    }

    @Operation(summary = "Get list all product by type")
    @GetMapping
    public ResponseEntity<?> getAllHardDrive() {
        return ResponseEntity.ok(hardDriveService.getAllProducts());
    }
}
