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
public class Token extends BaseAbsEntity {

    private Long userId;
    private String token;
    private String type;

}
