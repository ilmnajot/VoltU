package uz.ilmnajot.voltu.model.response;

import lombok.Getter;
import lombok.Setter;
import uz.ilmnajot.voltu.enums.UserRole;

@Setter
@Getter
public class UserResponse {

    private Long id;
    private String fullName;
    private String username;
    private String email;
    private String phone;
    private Double balance;
    private UserRole userRole;
    private boolean blocked;
    private Long chatId;
}
