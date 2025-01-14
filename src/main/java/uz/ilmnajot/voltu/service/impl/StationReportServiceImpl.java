package uz.ilmnajot.voltu.service.impl;

import org.springframework.stereotype.Service;
import uz.ilmnajot.voltu.repository.StationReportRepository;
import uz.ilmnajot.voltu.service.StationReportService;
@Service
public class StationReportServiceImpl implements StationReportService {

    private final StationReportRepository stationReportRepository;

    public StationReportServiceImpl(StationReportRepository stationReportRepository) {
        this.stationReportRepository = stationReportRepository;
    }
}
