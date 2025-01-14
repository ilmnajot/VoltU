package uz.ilmnajot.voltu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import uz.ilmnajot.voltu.enums.StationStatus;
import uz.ilmnajot.voltu.enums.StationType;
import uz.ilmnajot.voltu.template.BaseAbsEntity;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "charging_station")
@Builder
public class ChargingStation extends BaseAbsEntity {

    private String name;
    private Long ownerId;
    private Long transactionId;
    private Double maxPower;
    private Double pricePerKWH;
    private String location;
    private String address;
    private boolean isPublic;
    private String longitude;
    private String latitude;
    private Integer portsCount;
    private Long accountId;
    @Enumerated(EnumType.STRING)
    private StationType stationType;
    @Enumerated(EnumType.STRING)
    private StationStatus stationStatus;
}
