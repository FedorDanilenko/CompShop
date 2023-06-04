package jm.testEasyBot.CompShop.controller;

import jm.testEasyBot.CompShop.dto.DesktopComputerDto;
import jm.testEasyBot.CompShop.service.impl.DesktopComputerService;
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
@RequestMapping("/api/v1/product/desktop")
public class DesktopComputerController {

    private final DesktopComputerService desktopComputerService;

    @PostMapping
    public ResponseEntity<?> addDesktopComputer(@RequestBody DesktopComputerDto dto) {
        return ResponseEntity.ok(desktopComputerService.addNewProduct(dto));
    }

    @PatchMapping("/{serNum}")
    private ResponseEntity<?> updateDesktopComputer(@PathVariable String serNum,
                                                    @RequestBody DesktopComputerDto dto) {
        return ResponseEntity.ok(desktopComputerService.updateProduct(serNum, dto));
    }

    @GetMapping("/{serNum}")
    public ResponseEntity<?> getComputerById(@PathVariable String serNum) {
        return ResponseEntity.ok(desktopComputerService.getProduct(serNum));
    }

    @GetMapping
    public ResponseEntity<?> getAllDesktopComputers() {
        return ResponseEntity.ok(desktopComputerService.getAllProducts());
    }

}
