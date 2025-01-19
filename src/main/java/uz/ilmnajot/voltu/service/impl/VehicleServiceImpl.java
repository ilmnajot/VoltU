package uz.ilmnajot.voltu.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ilmnajot.voltu.entity.Vehicle;
import uz.ilmnajot.voltu.exception.ResourceNotFoundException;
import uz.ilmnajot.voltu.mapper.VehicleMapper;
import uz.ilmnajot.voltu.model.common.ApiResponse;
import uz.ilmnajot.voltu.model.request.VehicleRequestDTO;
import uz.ilmnajot.voltu.model.response.VehicleResponseDTO;
import uz.ilmnajot.voltu.repository.VehicleRepository;
import uz.ilmnajot.voltu.service.VehicleService;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleMapper = vehicleMapper;
    }

    @Override
    public ApiResponse addVehicle(VehicleRequestDTO vehicleRequestDTO) {
        Vehicle vehicle = this.getVehicleByNumber(vehicleRequestDTO.getNumber());
        Vehicle vehicleEntity = vehicleMapper.toVehicleEntity(vehicleRequestDTO);
        vehicle = this.vehicleRepository.save(vehicleEntity);
        VehicleResponseDTO vehicleDTO = this.vehicleMapper.toVehicleDTO(vehicle);
        return ApiResponse.builder()
                .httpStatus(HttpStatus.CREATED)
                .message("Vehicle Added Successfully")
                .data(vehicleDTO)
                .build();
    }

    @Override
    public ApiResponse getAllVehicles(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Vehicle> vehiclePage = this.vehicleRepository.findAll(pageable);
        List<VehicleResponseDTO> responseDTOList = vehiclePage
                .getContent()
                .stream()
                .map(this.vehicleMapper::toVehicleDTO)
                .toList();
        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .success(true)
                .data(responseDTOList)
                .build();
    }

    public Vehicle getVehicleByNumber(String vehicleNumber) {
        return this.vehicleRepository.findVehicleByNumber(vehicleNumber).orElseThrow(
                () -> new ResourceNotFoundException("Vehicle not found with number: " + vehicleNumber));
    }
}
