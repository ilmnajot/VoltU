package uz.ilmnajot.voltu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import uz.ilmnajot.voltu.enums.ChargingSessionStatus;
import uz.ilmnajot.voltu.template.BaseAbsEntity;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "charging_session")
@Builder
public class ChargingSession extends BaseAbsEntity {

    private Long userId;
    private Long stationId;
    private Long portId;
    private LocalDateTime statedTime;
    private LocalDateTime endTime;
    private Double consumedEnergy;
    private Double totalCost;
    @Enumerated(EnumType.STRING)
    private ChargingSessionStatus chargingSessionStatus;
}
