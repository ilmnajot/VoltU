package uz.ilmnajot.voltu.entity;

import jakarta.persistence.Entity;
import lombok.*;
import uz.ilmnajot.voltu.template.BaseAbsEntity;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Route extends BaseAbsEntity {
    private Long vehicleId;
    private String currentLocation;
    private String destination;
}
