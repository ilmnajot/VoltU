package uz.ilmnajot.voltu.service.impl;

import org.springframework.stereotype.Service;
import uz.ilmnajot.voltu.repository.ChargingSessionRepository;
import uz.ilmnajot.voltu.service.ChargingSessionService;
@Service
public class ChargingSessionServiceImpl implements ChargingSessionService {
    private final ChargingSessionRepository chargingSessionRepository;

    public ChargingSessionServiceImpl(ChargingSessionRepository chargingSessionRepository) {
        this.chargingSessionRepository = chargingSessionRepository;
    }
}
