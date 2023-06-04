package jm.testEasyBot.CompShop.mapper;

import jm.testEasyBot.CompShop.dto.HardDriveDto;
import jm.testEasyBot.CompShop.models.HardDrive;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface HardDriveMapper {

    HardDriveMapper MAPPER = Mappers.getMapper(HardDriveMapper.class);

    HardDriveDto toDto(HardDrive laptop);
    HardDrive toEntity(HardDriveDto dto);

}
