package jm.testEasyBot.CompShop.repository;

import jm.testEasyBot.CompShop.models.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepo extends JpaRepository<Laptop, Long> {

    Laptop findBySerialNumber(String num);
}
