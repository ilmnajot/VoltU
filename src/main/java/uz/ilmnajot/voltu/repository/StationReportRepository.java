package uz.ilmnajot.voltu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.voltu.entity.StationReport;

@Repository
public interface StationReportRepository extends JpaRepository<StationReport, Long> {
}
