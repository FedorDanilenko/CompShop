package jm.testEasyBot.CompShop.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto {

    public String serialNumber;
    public String manufacturer;
    public int price;
    public Long quantity;
}
