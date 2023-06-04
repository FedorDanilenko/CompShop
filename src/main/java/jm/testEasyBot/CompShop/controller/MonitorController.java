package jm.testEasyBot.CompShop.controller;


import jm.testEasyBot.CompShop.dto.MonitorDto;
import jm.testEasyBot.CompShop.service.impl.MonitorService;
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
@RequestMapping("/api/v1/product/monitor")
public class MonitorController {

    private final MonitorService monitorService;

    @PostMapping
    public ResponseEntity<?> addMonitor(@RequestBody MonitorDto dto) {
        return ResponseEntity.ok(monitorService.addNewProduct(dto));
    }

    @PatchMapping("/{serNum}")
    private ResponseEntity<?> updateMonitor(@PathVariable String serNum,
                                                    @RequestBody MonitorDto dto) {
        return ResponseEntity.ok(monitorService.updateProduct(serNum, dto));
    }

    @GetMapping("/{serNum}")
    public ResponseEntity<?> getMonitorById(@PathVariable String serNum) {
        return ResponseEntity.ok(monitorService.getProduct(serNum));
    }

    @GetMapping
    public ResponseEntity<?> getAllMonitors() {
        return ResponseEntity.ok(monitorService.getAllProducts());
    }
}
