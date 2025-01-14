package uz.ilmnajot.voltu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.voltu.entity.ChargingSession;

@Repository
public interface ChargingSessionRepository extends JpaRepository<ChargingSession, Long> {
}
