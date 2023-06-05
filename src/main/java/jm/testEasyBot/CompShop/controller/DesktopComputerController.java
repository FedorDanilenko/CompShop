package jm.testEasyBot.CompShop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jm.testEasyBot.CompShop.dto.DesktopComputerDto;
import jm.testEasyBot.CompShop.service.impl.DesktopComputerService;
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
@RequestMapping("/api/v1/product/desktop")
@Tag(name = "Desktop computers")
public class DesktopComputerController {

    private final DesktopComputerService desktopComputerService;

    @Operation(summary = "Add new desktop computer")
    @PostMapping
    public ResponseEntity<?> addDesktopComputer(@RequestBody DesktopComputerDto dto) {
        return ResponseEntity.ok(desktopComputerService.addNewProduct(dto));
    }

    @Operation(summary = "Update product info")
    @PutMapping("/{serNum}")
    private ResponseEntity<?> updateDesktopComputer(@PathVariable("serNum") String serNum,
                                                    @RequestBody DesktopComputerDto dto) {
        return ResponseEntity.ok(desktopComputerService.updateProduct(serNum, dto));
    }

    @Operation(summary = "Get product by type and serial number")
    @GetMapping("/{serNum}")
    public ResponseEntity<?> getComputerById(@PathVariable("serNum") String serNum) {
        return ResponseEntity.ok(desktopComputerService.getProduct(serNum));
    }

    @Operation(summary = "Get list all product by type")
    @GetMapping
    public ResponseEntity<?> getAllDesktopComputers() {
        return ResponseEntity.ok(desktopComputerService.getAllProducts());
    }

}
