package jm.testEasyBot.CompShop.mapper;

import jm.testEasyBot.CompShop.dto.MonitorDto;
import jm.testEasyBot.CompShop.models.Monitor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MonitorMapper {

    MonitorMapper MAPPER = Mappers.getMapper(MonitorMapper.class);

    MonitorDto toDto(Monitor monitor);
    Monitor toEntity(MonitorDto dto);

}
