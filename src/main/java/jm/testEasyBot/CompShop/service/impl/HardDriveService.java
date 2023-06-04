package jm.testEasyBot.CompShop.service.impl;

import jm.testEasyBot.CompShop.dto.HardDriveDto;
import jm.testEasyBot.CompShop.exeprion.AlreadyExistsException;
import jm.testEasyBot.CompShop.exeprion.NotFoundException;
import jm.testEasyBot.CompShop.mapper.HardDriveMapper;
import jm.testEasyBot.CompShop.models.HardDrive;
import jm.testEasyBot.CompShop.repository.HardDriveRepo;
import jm.testEasyBot.CompShop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HardDriveService implements ProductService<HardDriveDto> {

    private final HardDriveRepo hardDriveRepo;

    @Override
    public List<HardDriveDto> getAllProducts() {
        return hardDriveRepo.findAll().stream()
            .map(HardDriveMapper.MAPPER::toDto)
            .toList();
    }

    @Override
    public HardDriveDto getProduct(String serNum) {
        try {
            return HardDriveMapper.MAPPER.toDto(hardDriveRepo.findBySerialNumber(serNum));
        } catch (DataAccessException ex) {
            throw  new NotFoundException("Hard drive not found");
        }
    }


    @Override
    public HardDriveDto addNewProduct(HardDriveDto dto) {
        try {
            return HardDriveMapper.MAPPER.toDto(
                hardDriveRepo.save(HardDriveMapper.MAPPER.toEntity(dto))
            );
        } catch (DataAccessException ex) {
            throw new AlreadyExistsException("Hard drive with this serial number already exists");
        }
    }

    @Override
    public HardDriveDto updateProduct(String serialNumber, HardDriveDto dto) {
        HardDrive existProd = hardDriveRepo.findBySerialNumber(serialNumber);
        existProd.setSerialNumber(dto.getSerialNumber());
        existProd.setCapacity(dto.getCapacity());
        existProd.setManufacturer(dto.getManufacturer());
        existProd.setPrice(dto.getPrice());
        existProd.setQuantity(dto.getQuantity());
        try {
            return HardDriveMapper.MAPPER.toDto(
                hardDriveRepo.save(existProd)
            );
        } catch (DataAccessException ex) {
            throw new AlreadyExistsException("Hard drive with this serial number already exists");
        }
    }
}
