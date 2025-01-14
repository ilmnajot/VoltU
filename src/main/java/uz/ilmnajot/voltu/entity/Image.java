package uz.ilmnajot.voltu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import uz.ilmnajot.voltu.template.BaseAbsEntity;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "image")
@Builder
public class Image extends BaseAbsEntity {
    private Long size;
    @Column(columnDefinition = "TEXT", length = 1000)
    private String cloudPath;
    private String originName;
    private String contentType;
}
