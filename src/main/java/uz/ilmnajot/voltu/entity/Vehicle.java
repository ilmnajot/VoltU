package uz.ilmnajot.voltu.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.ilmnajot.voltu.enums.BatteryStatus;
import uz.ilmnajot.voltu.template.BaseAbsEntity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "vehicles")
@Builder
public class Vehicle extends BaseAbsEntity {

    private String name;
    private String vehicleMode;
    private String number;
    private Double batteryLevel;
    private Double powerReserve;
    private Double averageEnergyUsage;
    private String remainingTime;
    private Long userId;
    @Enumerated(EnumType.STRING)
    private BatteryStatus batteryStatus;
    private LocalDateTime activatedTime; // when it is activated, 17:00
    @ToString.Exclude
    @ManyToMany
    @JoinTable(
            name = "vehicles_images",
            joinColumns = @JoinColumn(name = "vehicle_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<Image> images = new HashSet<>();


}
