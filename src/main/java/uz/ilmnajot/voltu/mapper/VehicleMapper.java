package uz.ilmnajot.voltu.mapper;

import uz.ilmnajot.voltu.entity.Vehicle;
import uz.ilmnajot.voltu.model.request.VehicleRequestDTO;
import uz.ilmnajot.voltu.model.response.VehicleResponseDTO;

public interface VehicleMapper {

    Vehicle toVehicleEntity(VehicleRequestDTO vehicleRequestDTO);

    VehicleResponseDTO toVehicleDTO(Vehicle vehicle);
}
