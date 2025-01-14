package uz.ilmnajot.voltu.service.impl;

import org.springframework.stereotype.Service;
import uz.ilmnajot.voltu.repository.ChargingStationRepository;
import uz.ilmnajot.voltu.service.ChargingStationService;
@Service
public class ChargingStationServiceImpl implements ChargingStationService {

    private final ChargingStationRepository chargingStationRepository;

    public ChargingStationServiceImpl(ChargingStationRepository chargingStationRepository) {
        this.chargingStationRepository = chargingStationRepository;
    }
}
