package uz.ilmnajot.voltu.service.auth;

import uz.ilmnajot.voltu.entity.User;
import uz.ilmnajot.voltu.model.common.ApiResponse;
import uz.ilmnajot.voltu.model.request.AuthDTO;
import uz.ilmnajot.voltu.model.request.LoginDTO;
import uz.ilmnajot.voltu.model.request.VerifyDTO;

public interface AuthService {
    ApiResponse register(AuthDTO authDTO);

    User getUserByPhone(String phone);

    ApiResponse verifyBySms(VerifyDTO verifyDTO);

    ApiResponse login(LoginDTO loginDTO);
}
