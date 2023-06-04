package jm.testEasyBot.CompShop.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jm.testEasyBot.CompShop.models.enums.FromFactor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DesktopComputerDto extends ProductDto{

    private FromFactor fromFactor;
}
