package jm.testEasyBot.CompShop.mapper;

import jm.testEasyBot.CompShop.dto.DesktopComputerDto;
import jm.testEasyBot.CompShop.models.DesktopComputer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DesktopComputerMapper {

    DesktopComputerMapper IN = Mappers.getMapper(DesktopComputerMapper.class);

    DesktopComputerDto toDto(DesktopComputer desktopComputer);
    DesktopComputer toEntity(DesktopComputerDto dto);

}
