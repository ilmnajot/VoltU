package uz.ilmnajot.voltu.controller;

import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.voltu.model.common.ApiResponse;
import uz.ilmnajot.voltu.model.request.VehicleRequestDTO;
import uz.ilmnajot.voltu.service.VehicleService;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/create")
    public ApiResponse addVehicle(@RequestBody VehicleRequestDTO vehicleRequestDTO){
        return this.vehicleService.addVehicle(vehicleRequestDTO);
    }
    @GetMapping("/getAll")
    public ApiResponse getAllVehicles(@RequestParam(name = "page", defaultValue = "0") int page,
                                      @RequestParam(name = "size", defaultValue = "20") int size){
        return this.vehicleService.getAllVehicles(page, size);
    }
}
