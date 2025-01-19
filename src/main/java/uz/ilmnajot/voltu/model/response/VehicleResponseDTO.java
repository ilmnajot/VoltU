package uz.ilmnajot.voltu.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.ilmnajot.voltu.enums.BatteryStatus;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleResponseDTO {

    private Long id;
    private String name;
    private String number;
    private Double batteryLevel;
    private Double powerReserve;
    private Double averageEnergyUsage;
    private String remainingTime;
    private LocalDateTime activatedTime; // when it is activated, 17:00
    private Long userId;




}
