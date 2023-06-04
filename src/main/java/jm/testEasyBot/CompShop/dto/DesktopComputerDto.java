package jm.testEasyBot.CompShop.dto;

import jm.testEasyBot.CompShop.models.enums.FormFactor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DesktopComputerDto extends ProductDto{

    private FormFactor formFactor;
}
