package jm.testEasyBot.CompShop.service.impl;

import jm.testEasyBot.CompShop.dto.LaptopDto;
import jm.testEasyBot.CompShop.exeprion.AlreadyExistsException;
import jm.testEasyBot.CompShop.exeprion.NotFoundException;
import jm.testEasyBot.CompShop.mapper.LaptopMapper;
import jm.testEasyBot.CompShop.models.Laptop;
import jm.testEasyBot.CompShop.repository.LaptopRepo;
import jm.testEasyBot.CompShop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LaptopService implements ProductService<LaptopDto> {

    private final LaptopRepo laptopRepo;

    @Override
    public List<LaptopDto> getAllProducts() {
        return laptopRepo.findAll().stream()
            .map(LaptopMapper.MAPPER::toDto)
            .toList();
    }

    @Override
    public LaptopDto getProduct(String serNum) {
        try {
            return LaptopMapper.MAPPER.toDto(findBySerialNumber(serNum));
        } catch (DataAccessException ex) {
            throw  new NotFoundException("Laptop not found");
        }
    }


    @Override
    public LaptopDto addNewProduct(LaptopDto dto) {
        try {
            return LaptopMapper.MAPPER.toDto(
                laptopRepo.save(LaptopMapper.MAPPER.toEntity(dto))
            );
        } catch (DataAccessException ex) {
            throw new AlreadyExistsException("Laptop with this serial number already exists");
        }
    }

    @Override
    public LaptopDto updateProduct(String serialNumber, LaptopDto dto) {
        Laptop existProd = findBySerialNumber(serialNumber);
        existProd.setSerialNumber(dto.getSerialNumber());
        existProd.setSize(dto.getSize());
        existProd.setManufacturer(dto.getManufacturer());
        existProd.setPrice(dto.getPrice());
        existProd.setQuantity(dto.getQuantity());
        try {
            return LaptopMapper.MAPPER.toDto(
                laptopRepo.save(existProd)
            );
        } catch (DataAccessException ex) {
            throw new AlreadyExistsException("Laptop with this serial number already exists");
        }
    }

    private Laptop findBySerialNumber(String num) {
        Laptop laptop = laptopRepo.findBySerialNumber(num);
        if (laptop == null) {
            throw new NotFoundException("Laptop computer not found");
        }
        return laptop;
    }
}
