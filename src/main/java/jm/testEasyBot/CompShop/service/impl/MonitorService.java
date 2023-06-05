package jm.testEasyBot.CompShop.service.impl;

import jm.testEasyBot.CompShop.dto.MonitorDto;
import jm.testEasyBot.CompShop.exeprion.AlreadyExistsException;
import jm.testEasyBot.CompShop.exeprion.NotFoundException;
import jm.testEasyBot.CompShop.mapper.MonitorMapper;
import jm.testEasyBot.CompShop.models.Monitor;
import jm.testEasyBot.CompShop.repository.MonitorRepo;
import jm.testEasyBot.CompShop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MonitorService implements ProductService<MonitorDto> {

    private final MonitorRepo monitorRepo;

    @Override
    public List<MonitorDto> getAllProducts() {
        return monitorRepo.findAll().stream()
            .map(MonitorMapper.MAPPER::toDto)
            .toList();
    }

    @Override
    public MonitorDto getProduct(String serNum) {
        try {
            return MonitorMapper.MAPPER.toDto(findBySerialNumber(serNum));
        } catch (DataAccessException ex) {
            throw new NotFoundException("Monitor not found");
        }
    }


    @Override
    public MonitorDto addNewProduct(MonitorDto dto) {
        try {
            return MonitorMapper.MAPPER.toDto(
                monitorRepo.save(MonitorMapper.MAPPER.toEntity(dto))
            );
        } catch (DataAccessException ex) {
            throw new AlreadyExistsException("Monitor with this serial number already exists");
        }
    }

    @Override
    public MonitorDto updateProduct(String serialNumber, MonitorDto dto) {
        Monitor existProd = findBySerialNumber(serialNumber);
        existProd.setSerialNumber(dto.getSerialNumber());
        existProd.setDiagonalSize(dto.getDiagonalSize());
        existProd.setManufacturer(dto.getManufacturer());
        existProd.setPrice(dto.getPrice());
        existProd.setQuantity(dto.getQuantity());
        try {
            return MonitorMapper.MAPPER.toDto(
                monitorRepo.save(existProd)
            );
        } catch (DataAccessException ex) {
            throw new AlreadyExistsException("Monitor with this serial number already exists");
        }
    }

    private Monitor findBySerialNumber(String num) {
        Monitor monitor = monitorRepo.findBySerialNumber(num);
        if (monitor == null) {
            throw new NotFoundException("Monitor computer not found");
        }
        return monitor;
    }
}
