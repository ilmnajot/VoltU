package uz.ilmnajot.voltu.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.ilmnajot.voltu.entity.Code;
import uz.ilmnajot.voltu.entity.User;
import uz.ilmnajot.voltu.service.CodeService;
import uz.ilmnajot.voltu.service.UserService;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class Utils {
    private final UserService userService;
    private final CodeService codeService;

    public boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
            return phoneNumber.matches("^[0-9]{9}$");
        } else {
            return false;
        }
    }

    public String getCode() {
        Random random = new Random();
        int code = 10000 + random.nextInt(90000);
        return String.valueOf(code);
    }

    public boolean checkCode(String phone, String code) {
        User user = this.userService.findByPhone(phone);
        Code codeByUserId = this.codeService.findCodeByUserId(user.getId());
        String c = codeByUserId.getCode();
        return c.equals(code);
    }
}
