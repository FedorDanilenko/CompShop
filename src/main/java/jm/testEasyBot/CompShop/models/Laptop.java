package jm.testEasyBot.CompShop.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jm.testEasyBot.CompShop.models.enums.LaptopSize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Laptops")
public class Laptop extends Product {

    @Column(name = "screen_size")
    @Enumerated(EnumType.STRING)
    private LaptopSize size;
}
