package jm.testEasyBot.CompShop.service;

import jm.testEasyBot.CompShop.dto.DesktopComputerDto;
import jm.testEasyBot.CompShop.mapper.DesktopComputerMapper;
import jm.testEasyBot.CompShop.models.DesktopComputer;
import jm.testEasyBot.CompShop.repository.DesktopComputerRepo;
import jm.testEasyBot.CompShop.repository.LaptopRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DesktopComputerService {

    private final DesktopComputerRepo desktopComputerRepo;

    public List<DesktopComputerDto> getAllComputers() {
        return desktopComputerRepo.findAll().stream()
            .map(DesktopComputerMapper.MAPPER::toDto)
            .toList();
    }

    public DesktopComputerDto getCompById(Long id) {
        return DesktopComputerMapper.MAPPER.toDto(desktopComputerRepo.findById(id).orElseThrow(() ->
            new RuntimeException("Desktop computer not found")));
    }


}
