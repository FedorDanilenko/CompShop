package jm.testEasyBot.CompShop.controller;

import jm.testEasyBot.CompShop.dto.DesktopComputerDto;
import jm.testEasyBot.CompShop.models.DesktopComputer;
import jm.testEasyBot.CompShop.service.DesktopComputerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.LinkOption;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product/desktop")
public class DesktopComputerController {

    private final DesktopComputerService desktopComputerService;

    @PostMapping


    @GetMapping("/{id}")
    public ResponseEntity<?> getComputerById(@PathVariable Long id) {
        return ResponseEntity.ok(desktopComputerService.getCompById(id));
    }
    @GetMapping
    public ResponseEntity<?> getAllDesktopComputers() {
        return ResponseEntity.ok(desktopComputerService.getAllComputers());
    }

}
