package jm.testEasyBot.CompShop.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class HardDriveDto extends ProductDto {

    private int capacity;
}
