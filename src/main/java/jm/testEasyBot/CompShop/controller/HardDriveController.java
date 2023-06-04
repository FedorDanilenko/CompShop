package jm.testEasyBot.CompShop.controller;

import jm.testEasyBot.CompShop.dto.HardDriveDto;
import jm.testEasyBot.CompShop.service.impl.HardDriveService;
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
@RequestMapping("/api/v1/product/hard-drive")
public class HardDriveController {

    private final HardDriveService hardDriveService;

    @PostMapping
    public ResponseEntity<?> addHardDrive(@RequestBody HardDriveDto dto) {
        return ResponseEntity.ok(hardDriveService.addNewProduct(dto));
    }

    @PatchMapping("/{serNum}")
    private ResponseEntity<?> updateHardDrive(@PathVariable String serNum,
                                           @RequestBody HardDriveDto dto) {
        return ResponseEntity.ok(hardDriveService.updateProduct(serNum, dto));
    }

    @GetMapping("/{serNum}")
    public ResponseEntity<?> getHardDriveById(@PathVariable String serNum) {
        return ResponseEntity.ok(hardDriveService.getProduct(serNum));
    }

    @GetMapping
    public ResponseEntity<?> getAllHardDrive() {
        return ResponseEntity.ok(hardDriveService.getAllProducts());
    }
}
