package uz.ilmnajot.voltu.model.request;

import lombok.*;

@Setter
@Getter
public class AuthDTO {

    private String fullName;
    private String username;
    private String email;
    private String phone;
//    private String password;
//    private Double balance;
    private String deviceName;
}
