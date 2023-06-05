package jm.testEasyBot.CompShop.mapper;

import jm.testEasyBot.CompShop.dto.LaptopDto;
import jm.testEasyBot.CompShop.models.Laptop;
import org.springframework.stereotype.Component;

@Component
public class LaptopMapper {

    public LaptopDto toDto(Laptop entity) {
        if (entity == null) return null;
        LaptopDto dto = new LaptopDto();
        dto.setSerialNumber(entity.getSerialNumber());
        dto.setManufacturer(entity.getManufacturer());
        dto.setPrice(entity.getPrice());
        dto.setQuantity(entity.getQuantity());
        dto.setSize(entity.getSize());
        return dto;
    }
    public Laptop toEntity(LaptopDto dto) {
        if (dto == null) return null;
        Laptop entity = new Laptop();
        entity.setSerialNumber(dto.getSerialNumber());
        entity.setManufacturer(dto.getManufacturer());
        entity.setPrice(dto.getPrice());
        entity.setQuantity(dto.getQuantity());
        entity.setSize(dto.getSize());
        return entity;
    }

}
