package uz.ilmnajot.voltu.service.impl;

import org.springframework.stereotype.Service;
import uz.ilmnajot.voltu.repository.RouteRepository;
import uz.ilmnajot.voltu.service.RouteService;
@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }
}
