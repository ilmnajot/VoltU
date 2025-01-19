package uz.ilmnajot.voltu.service;

import uz.ilmnajot.voltu.model.common.ApiResponse;
import uz.ilmnajot.voltu.model.request.VehicleRequestDTO;

public interface VehicleService {
    ApiResponse addVehicle(VehicleRequestDTO vehicleRequestDTO);

    ApiResponse getAllVehicles(int page, int size);
}
