package uz.ilmnajot.voltu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import uz.ilmnajot.voltu.enums.TransactionType;
import uz.ilmnajot.voltu.template.BaseAbsEntity;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "transactions")
@Builder
public class Transactions extends BaseAbsEntity {
    private Long userId;
    private Long chargerId;
    private Double amount;
    private Double energyAmount;
    private Double spendTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
}
