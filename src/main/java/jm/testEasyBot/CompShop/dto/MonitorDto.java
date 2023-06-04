package jm.testEasyBot.CompShop.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MonitorDto extends ProductDto{

    private int diagonalSize;
}
