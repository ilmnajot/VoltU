package uz.ilmnajot.voltu.controller.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ilmnajot.voltu.model.common.ApiResponse;
import uz.ilmnajot.voltu.model.request.AuthDTO;
import uz.ilmnajot.voltu.model.request.LoginDTO;
import uz.ilmnajot.voltu.model.request.VerifyDTO;
import uz.ilmnajot.voltu.service.auth.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ApiResponse register(@RequestBody AuthDTO authDTO) {
        return this.authService.register(authDTO);
    }

    @PostMapping("/verify")
    public ApiResponse verify(@RequestBody VerifyDTO verifyDTO) {
        return this.authService.verifyBySms(verifyDTO);
    }

    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginDTO loginDTO) {
        return this.authService.login(loginDTO);
    }
}
