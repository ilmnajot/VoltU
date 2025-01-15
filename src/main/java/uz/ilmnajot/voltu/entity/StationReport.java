package uz.ilmnajot.voltu.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.ilmnajot.voltu.enums.ReportType;
import uz.ilmnajot.voltu.template.BaseAbsEntity;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "station_report")
@Builder

public class StationReport extends BaseAbsEntity {
    private Long stationId;
    private Double totalIncome;
    private Double energyConsumed;
    //**************added*****************//
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @Enumerated(EnumType.STRING)
    private ReportType reportType;


}
