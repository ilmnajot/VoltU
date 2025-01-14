package uz.ilmnajot.voltu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ilmnajot.voltu.enums.ChargingSessionStatus;
import uz.ilmnajot.voltu.service.ChargingStationService;

@RestController
@RequestMapping("/api")
public class ChargingStationController {
    private final ChargingStationService chargingStationService;

}
