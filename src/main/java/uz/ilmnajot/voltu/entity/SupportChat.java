package uz.ilmnajot.voltu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import uz.ilmnajot.voltu.enums.SenderType;
import uz.ilmnajot.voltu.template.BaseAbsEntity;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "support_chat")
@Builder
public class SupportChat extends BaseAbsEntity {
    private Long userId;
    @Column(columnDefinition = "TEXT")
    private String message;
    @Enumerated(EnumType.STRING)
    private SenderType senderType;
}
