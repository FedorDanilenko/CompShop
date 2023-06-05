package jm.testEasyBot.CompShop.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jm.testEasyBot.CompShop.dto.MonitorDto;
import jm.testEasyBot.CompShop.service.impl.MonitorService;
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
@RequestMapping("/api/v1/product/monitor")
@Tag(name = "Monitors")
public class MonitorController {

    private final MonitorService monitorService;

    @Operation(summary = "Add new monitor")
    @PostMapping
    public ResponseEntity<?> addMonitor(@RequestBody MonitorDto dto) {
        return ResponseEntity.ok(monitorService.addNewProduct(dto));
    }

    @Operation(summary = "Update product info")
    @PutMapping("/{serNum}")
    private ResponseEntity<?> updateMonitor(@PathVariable("serNum") String serNum,
                                                    @RequestBody MonitorDto dto) {
        return ResponseEntity.ok(monitorService.updateProduct(serNum, dto));
    }

    @Operation(summary = "Get product by type and serial number")
    @GetMapping("/{serNum}")
    public ResponseEntity<?> getMonitorById(@PathVariable("serNum") String serNum) {
        return ResponseEntity.ok(monitorService.getProduct(serNum));
    }

    @Operation(summary = "Get list all product by type")
    @GetMapping
    public ResponseEntity<?> getAllMonitors() {
        return ResponseEntity.ok(monitorService.getAllProducts());
    }
}
