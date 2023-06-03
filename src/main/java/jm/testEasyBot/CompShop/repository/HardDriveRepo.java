package jm.testEasyBot.CompShop.repository;

import jm.testEasyBot.CompShop.models.HardDrive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HardDriveRepo extends JpaRepository<HardDrive, Long> {
}
