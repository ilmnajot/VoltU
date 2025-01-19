package uz.ilmnajot.voltu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.voltu.entity.Vehicle;
import uz.ilmnajot.voltu.model.request.VehicleRequestDTO;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findVehicleByNumber(String vehicleNumber);

    @Query(value = "select * from vehicles LIMIT :size offset :offset", nativeQuery = true)
    List<Vehicle> findAllVehicles(@Param("offset") Integer offset,
                                  @Param("size") Integer size);
}
