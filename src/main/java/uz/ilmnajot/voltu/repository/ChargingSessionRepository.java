package uz.ilmnajot.voltu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.voltu.entity.ChargingSession;

import java.util.List;

@Repository
public interface ChargingSessionRepository extends JpaRepository<ChargingSession, Long> {

    @Query("select  sum(cs.totalCost) from charging_session as cs where cs.userId=:userId group by cs.id")
    Double getTotalSpentByUserId(@Param("userId") Long userId);

    @Query("select sum(cs.consumedEnergy) from charging_session as cs where cs.userId =:userId group by cs.id")
    Double getTotalChargedByUserId(@Param("userId") Long userId);

    @Query(value = "select SUM(TIMESTAMPDIFF(MINUTE, cs.statedTime, cs.endTime)) from charging_session as cs where cs.userId=:userId and cs.endTime is not null group by cs.id", nativeQuery = true)
    Double getChargingTotalTime(@Param("userId") Long userId);

    @Query("select distinct cs.stationId from charging_session  as cs where cs.userId=:userId group by cs.id")
    List<Long> getVisitedStationsByUserId(@Param("userId") Long userId);

    @Query(value = "select SUM(TIMESTAMPDIFF(MINUTE, cs.statedTime, cs.endTime)) from charging_session as cs where cs.userId =:userId group by ", nativeQuery = true)
    Double totalParkingTimeByUserId(@Param("userId") Long userId);


    @Query("select count(cs.id) from charging_session as cs where cs.userId=:userId group by cs.id")
    Integer getTotalCyclesByUser(@Param("userId") Long userId);

}
