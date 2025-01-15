package uz.ilmnajot.voltu.service.auth;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private final AuthService authService;

    public CustomUserDetailsServiceImpl(@Lazy AuthService authService) {
        this.authService = authService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authService.getUserByPhone(username);
    }
}
