package uz.ilmnajot.voltu.service.auth;

import uz.ilmnajot.voltu.entity.User;
import uz.ilmnajot.voltu.model.common.ApiResponse;
import uz.ilmnajot.voltu.model.request.AuthDTO;

public interface AuthService {
    ApiResponse register(AuthDTO authDTO);
    User getUserByPhone(String phone);
}
