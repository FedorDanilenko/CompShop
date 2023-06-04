package jm.testEasyBot.CompShop.models;


import jakarta.persistence.Column;
import jm.testEasyBot.CompShop.models.enums.FormFactor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Table;
import jakarta.persistence.Enumerated;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Desktop_Computers")
public class DesktopComputer extends Product {

    @Column(name = "from_factor")
    @Enumerated(EnumType.STRING)
    private FormFactor formFactor;
}
