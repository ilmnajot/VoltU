package uz.ilmnajot.voltu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import uz.ilmnajot.voltu.enums.PortType;
import uz.ilmnajot.voltu.template.BaseAbsEntity;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Port extends BaseAbsEntity {

    private Long chargerId;
    private Long vehicleId;
    private boolean occupied;
    @Enumerated(EnumType.STRING)
    private PortType portType;
}
