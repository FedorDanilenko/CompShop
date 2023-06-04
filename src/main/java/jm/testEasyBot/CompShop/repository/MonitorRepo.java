package jm.testEasyBot.CompShop.repository;

import jm.testEasyBot.CompShop.models.DesktopComputer;
import jm.testEasyBot.CompShop.models.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitorRepo extends JpaRepository<Monitor, Long> {

    Monitor findBySerialNumber(String num);
}
