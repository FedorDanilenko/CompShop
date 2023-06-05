package jm.testEasyBot.CompShop.mapper;

import jm.testEasyBot.CompShop.dto.DesktopComputerDto;
import jm.testEasyBot.CompShop.models.DesktopComputer;
import org.springframework.stereotype.Component;

@Component
public class DesktopComputerMapper {

     public DesktopComputerDto toDto(DesktopComputer entity) {
        if (entity == null) return null;
        DesktopComputerDto dto = new DesktopComputerDto();
        dto.setSerialNumber(entity.getSerialNumber());
        dto.setManufacturer(entity.getManufacturer());
        dto.setPrice(entity.getPrice());
        dto.setQuantity(entity.getQuantity());
        dto.setFormFactor(entity.getFormFactor());
        return dto;
    }
     public DesktopComputer toEntity(DesktopComputerDto dto) {
        if (dto == null) return null;
        DesktopComputer entity = new DesktopComputer();
        entity.setSerialNumber(dto.getSerialNumber());
        entity.setManufacturer(dto.getManufacturer());
        entity.setPrice(dto.getPrice());
        entity.setQuantity(dto.getQuantity());
        entity.setFormFactor(dto.getFormFactor());
        return entity;
    }
}
