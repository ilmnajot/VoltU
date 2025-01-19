package uz.ilmnajot.voltu.model.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginDTO {

    private String phoneNumber;
    private String password;
    private String deviceName;

}
