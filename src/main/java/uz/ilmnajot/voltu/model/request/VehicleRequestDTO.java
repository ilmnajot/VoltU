package uz.ilmnajot.voltu.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRequestDTO {

    private String name;
    private String number;
    private Double batteryLevel;
    private Double powerReserve;
    private Double averageEnergyUsage;
    private String remainingTime;
    private LocalDateTime activatedTime; // when it is activated, 17:00
    private Long userId;




}
