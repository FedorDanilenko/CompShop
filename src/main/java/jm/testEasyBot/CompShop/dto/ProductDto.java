package jm.testEasyBot.CompShop.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto {

    private String serialNumber;
    private String manufacturer;
    private int price;
    private Long quantity;
}
