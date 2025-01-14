package uz.ilmnajot.voltu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.voltu.entity.ChargingStation;

@Repository
public interface ChargingStationRepository extends JpaRepository<ChargingStation, Long> {
}
