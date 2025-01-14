package uz.ilmnajot.voltu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import uz.ilmnajot.voltu.template.BaseAbsEntity;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class UserChat extends BaseAbsEntity {

    private Long senderId;
    private Long receiverId;
    @Column(columnDefinition = "TEXT")
    private String message;
    //send at
}
