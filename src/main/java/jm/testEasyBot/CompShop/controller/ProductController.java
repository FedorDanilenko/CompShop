package jm.testEasyBot.CompShop.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jm.testEasyBot.CompShop.dto.MonitorDto;
import jm.testEasyBot.CompShop.models.enums.ProductType;
import jm.testEasyBot.CompShop.service.impl.DesktopComputerService;
import jm.testEasyBot.CompShop.service.impl.HardDriveService;
import jm.testEasyBot.CompShop.service.impl.LaptopService;
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
@RequestMapping("/api/v1/product")
@Tag(name = "Products")
public class ProductController {

    private final DesktopComputerService desktopComputerService;
    private final HardDriveService hardDriveService;
    private final MonitorService monitorService;
    private final LaptopService laptopService;

    @Operation(summary = "Add new product")
    @PostMapping("/{type}")
    public ResponseEntity<?> addMonitor(@PathVariable("type") ProductType type,
                                        @RequestBody MonitorDto dto) {
        ResponseEntity<?> responce = null;
        switch (type) {
            case LAPTOP -> {
                responce = ResponseEntity.ok(laptopService.addNewProduct(null));
            }
            case MONITOR -> {
                responce = ResponseEntity.ok(monitorService.addNewProduct(dto));
            }
            case DESKTOP_COMPUTER -> {
                responce = ResponseEntity.ok(desktopComputerService.addNewProduct(null));
            }
            case HARD_DRIVE -> {
                responce = ResponseEntity.ok(hardDriveService.addNewProduct(null));
            }
        }
        return responce;
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
