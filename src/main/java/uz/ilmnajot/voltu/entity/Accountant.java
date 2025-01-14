package uz.ilmnajot.voltu.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import uz.ilmnajot.voltu.template.BaseAbsEntity;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "accountant")
@Builder
public class Accountant extends BaseAbsEntity {
    private String name;
    private String number;
    private String address;
    private Double value;

    @OneToMany(mappedBy = "accountId", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Set<ChargingStation> chargingStations = new HashSet<>();
}
