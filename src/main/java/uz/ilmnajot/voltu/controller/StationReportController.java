package uz.ilmnajot.voltu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ilmnajot.voltu.entity.StationReport;
import uz.ilmnajot.voltu.service.StationReportService;

@RestController
@RequestMapping("/api")
public class StationReportController {
    private final StationReportService stationReportService;

}
