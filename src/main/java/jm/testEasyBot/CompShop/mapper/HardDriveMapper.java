package jm.testEasyBot.CompShop.mapper;

import jm.testEasyBot.CompShop.dto.HardDriveDto;
import jm.testEasyBot.CompShop.models.HardDrive;
import org.springframework.stereotype.Component;

@Component
public class HardDriveMapper {

    public HardDriveDto toDto(HardDrive entity) {
        if (entity == null) return null;
        HardDriveDto dto = new HardDriveDto();
        dto.setSerialNumber(entity.getSerialNumber());
        dto.setManufacturer(entity.getManufacturer());
        dto.setPrice(entity.getPrice());
        dto.setQuantity(entity.getQuantity());
        dto.setCapacity(entity.getCapacity());
        return dto;
    }
    public HardDrive toEntity(HardDriveDto dto) {
        if (dto == null) return null;
        HardDrive entity = new HardDrive();
        entity.setSerialNumber(dto.getSerialNumber());
        entity.setManufacturer(dto.getManufacturer());
        entity.setPrice(dto.getPrice());
        entity.setQuantity(dto.getQuantity());
        entity.setCapacity(dto.getCapacity());
        return entity;
    }

}
