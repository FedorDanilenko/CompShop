package jm.testEasyBot.CompShop.mapper;

import jm.testEasyBot.CompShop.dto.MonitorDto;
import jm.testEasyBot.CompShop.models.Monitor;
import org.springframework.stereotype.Component;

@Component
public class MonitorMapper {

    public MonitorDto toDto(Monitor entity) {
        if (entity == null) return null;
        MonitorDto dto = new MonitorDto();
        dto.setSerialNumber(entity.getSerialNumber());
        dto.setManufacturer(entity.getManufacturer());
        dto.setPrice(entity.getPrice());
        dto.setQuantity(entity.getQuantity());
        dto.setDiagonalSize(entity.getDiagonalSize());
        return dto;
    }
    public Monitor toEntity(MonitorDto dto) {
        if (dto == null) return null;
        Monitor entity = new Monitor();
        entity.setSerialNumber(dto.getSerialNumber());
        entity.setManufacturer(dto.getManufacturer());
        entity.setPrice(dto.getPrice());
        entity.setQuantity(dto.getQuantity());
        entity.setDiagonalSize(dto.getDiagonalSize());
        return entity;
    }

}
