package jm.testEasyBot.CompShop.service.impl;

import jm.testEasyBot.CompShop.dto.DesktopComputerDto;
import jm.testEasyBot.CompShop.exeprion.AlreadyExistsException;
import jm.testEasyBot.CompShop.exeprion.NotFoundException;
import jm.testEasyBot.CompShop.mapper.DesktopComputerMapper;
import jm.testEasyBot.CompShop.models.DesktopComputer;
import jm.testEasyBot.CompShop.repository.DesktopComputerRepo;
import jm.testEasyBot.CompShop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DesktopComputerService implements ProductService<DesktopComputerDto> {

    private final DesktopComputerRepo desktopComputerRepo;

    @Override
    public List<DesktopComputerDto> getAllProducts() {
        return desktopComputerRepo.findAll().stream()
            .map(DesktopComputerMapper.MAPPER::toDto)
            .toList();
    }

    @Override
    public DesktopComputerDto getProduct(String serNum) {
        try {
        return DesktopComputerMapper.MAPPER.toDto(desktopComputerRepo.findBySerialNumber(serNum));
        } catch (DataAccessException ex) {
           throw  new NotFoundException("Desktop computer not found");
        }
    }


    @Override
    public DesktopComputerDto addNewProduct(DesktopComputerDto dto) {
        System.out.println(DesktopComputerMapper.MAPPER.toEntity(dto));
        try {
            return DesktopComputerMapper.MAPPER.toDto(
                desktopComputerRepo.save(DesktopComputerMapper.MAPPER.toEntity(dto))
            );
        } catch (DataAccessException ex) {
            throw new AlreadyExistsException("Computer with this serial number already exists");
        }
    }

    @Override
    public DesktopComputerDto updateProduct(String serialNumber, DesktopComputerDto dto) {
        DesktopComputer existComp = desktopComputerRepo.findBySerialNumber(serialNumber);
        existComp.setSerialNumber(dto.getSerialNumber());
        existComp.setFromFactor(dto.getFromFactor());
        existComp.setManufacturer(dto.getManufacturer());
        existComp.setPrice(dto.getPrice());
        existComp.setQuantity(dto.getQuantity());
        return DesktopComputerMapper.MAPPER.toDto(
            desktopComputerRepo.save(existComp)
        );
    }
}
