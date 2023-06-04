package jm.testEasyBot.CompShop.dto;

import jm.testEasyBot.CompShop.models.enums.LaptopSize;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LaptopDto extends ProductDto{

    private LaptopSize size;
}
