package uz.ilmnajot.voltu.mapper;

import org.springframework.stereotype.Component;
import uz.ilmnajot.voltu.entity.Vehicle;
import uz.ilmnajot.voltu.model.request.VehicleRequestDTO;
import uz.ilmnajot.voltu.model.response.VehicleResponseDTO;
import uz.ilmnajot.voltu.service.UserService;

@Component
public class VehicleMapperImpl implements VehicleMapper {

    private final UserService userService;

    public VehicleMapperImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Vehicle toVehicleEntity(VehicleRequestDTO vehicleRequestDTO) {
        Vehicle vehicle = new Vehicle();
        vehicle.setName(vehicleRequestDTO.getName());
        vehicle.setNumber(vehicleRequestDTO.getNumber());
        vehicle.setBatteryLevel(vehicleRequestDTO.getBatteryLevel());
        vehicle.setPowerReserve(vehicleRequestDTO.getPowerReserve());
        vehicle.setAverageEnergyUsage(vehicleRequestDTO.getAverageEnergyUsage());
        vehicle.setRemainingTime(vehicleRequestDTO.getRemainingTime());
        vehicle.setActivatedTime(vehicleRequestDTO.getActivatedTime());
        this.userService.getUserById(vehicleRequestDTO.getUserId());
        return vehicle;
    }

    @Override
    public VehicleResponseDTO toVehicleDTO(Vehicle vehicle) {
        VehicleResponseDTO responseDTO = new VehicleResponseDTO();
        responseDTO.setId(vehicle.getId());
        responseDTO.setName(vehicle.getName());
        responseDTO.setBatteryLevel(vehicle.getBatteryLevel());
        responseDTO.setPowerReserve(vehicle.getPowerReserve());
        responseDTO.setAverageEnergyUsage(vehicle.getAverageEnergyUsage());
        responseDTO.setRemainingTime(vehicle.getRemainingTime());
        responseDTO.setActivatedTime(vehicle.getActivatedTime());
        responseDTO.setUserId(vehicle.getUserId());
        return responseDTO;
    }
}
