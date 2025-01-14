package uz.ilmnajot.voltu.entity;

import jakarta.persistence.Entity;
import lombok.*;
import uz.ilmnajot.voltu.template.BaseAbsEntity;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "trusted_device")
@Builder
public class TrustedDevice extends BaseAbsEntity {
    private Long userId;
    private String deviceName;
    private String macAddress;
    private LocalDateTime addedAt;
    private LocalDateTime expiredAt;
}
