package uz.ilmnajot.voltu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;
import uz.ilmnajot.voltu.jwt.JwtFilter;
import uz.ilmnajot.voltu.service.auth.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
     return http
              .cors(AbstractHttpConfigurer::disable)
              .csrf(AbstractHttpConfigurer::disable)
              .authorizeHttpRequests(request->{
                  request.requestMatchers("/swagger-ui/**",
                          "/auth/**",
                          "/users/**",
                          "/account/**",
                          "/image/**",
                          "/charging-station/**",
                          "/sessions/**",
                          "/transactions/**",
                          "/support-chat/**",
                          "/station-reports/**",
                          "/user-chat/**",
                          "/payment/**",
                          "/vehicle/**",
                          "/swagger-ui.html",
                          "/webjars/**",
                          "/v3/api-docs/**")
                          .permitAll()
                          .anyRequest()
                          .authenticated();
              })
              .sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
              .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
              .headers(header->header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
//              .formLogin(form->form.successHandler(handler))
              .build();
    }

}
