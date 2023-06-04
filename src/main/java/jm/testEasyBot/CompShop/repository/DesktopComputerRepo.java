package jm.testEasyBot.CompShop.repository;

import jm.testEasyBot.CompShop.models.DesktopComputer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesktopComputerRepo extends JpaRepository<DesktopComputer, Long> {

    DesktopComputer findBySerialNumber(String num);
}
