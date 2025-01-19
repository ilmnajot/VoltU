package uz.ilmnajot.voltu.model.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VerifyDTO {

    String code;
    String phoneNumber;
    String password;
}
