package uz.ilmnajot.voltu.model.request;

import lombok.Getter;
import lombok.Setter;
import uz.ilmnajot.voltu.enums.UserRole;

@Setter
@Getter
public class AuthDTO {

    private String fullName;
    private String username;
    private String email;
    private String phone;
    private String password;
    private Double balance;
    private UserRole userRole;
    private boolean blocked;
}
