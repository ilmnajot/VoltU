package uz.ilmnajot.voltu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ilmnajot.voltu.service.ChargingSessionService;

@RestController
@RequestMapping("/api")
public class ChargingSessionController {

    private final ChargingSessionService chargingSessionService;

    public ChargingSessionController(ChargingSessionService chargingSessionService) {
        this.chargingSessionService = chargingSessionService;
    }
}
