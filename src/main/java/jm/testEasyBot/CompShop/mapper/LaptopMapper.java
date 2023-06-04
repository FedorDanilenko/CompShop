package jm.testEasyBot.CompShop.mapper;

import jm.testEasyBot.CompShop.dto.LaptopDto;
import jm.testEasyBot.CompShop.models.Laptop;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LaptopMapper {

    LaptopMapper MAPPER = Mappers.getMapper(LaptopMapper.class);

    LaptopDto toDto(Laptop laptop);
    Laptop toEntity(LaptopDto dto);

}
