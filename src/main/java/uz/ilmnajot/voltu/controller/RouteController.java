package uz.ilmnajot.voltu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ilmnajot.voltu.entity.Route;
import uz.ilmnajot.voltu.service.RouteService;

@RestController
@RequestMapping("/api")
public class RouteController {

    private final RouteService routeService;

}
