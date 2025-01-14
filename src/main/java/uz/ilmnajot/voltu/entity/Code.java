package uz.ilmnajot.voltu.entity;

import jakarta.persistence.Entity;
import lombok.*;
import uz.ilmnajot.voltu.template.BaseAbsEntity;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "code")
@Builder
public class Code extends BaseAbsEntity {

    private String code;
    private Long userId;
}
