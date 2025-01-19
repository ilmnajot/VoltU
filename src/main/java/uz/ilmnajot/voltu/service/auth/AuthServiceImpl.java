package uz.ilmnajot.voltu.service.auth;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.ilmnajot.voltu.entity.Code;
import uz.ilmnajot.voltu.entity.Token;
import uz.ilmnajot.voltu.entity.TrustedDevice;
import uz.ilmnajot.voltu.entity.User;
import uz.ilmnajot.voltu.enums.UserRole;
import uz.ilmnajot.voltu.jwt.JwtProvider;
import uz.ilmnajot.voltu.mapper.UserMapper;
import uz.ilmnajot.voltu.model.common.ApiResponse;
import uz.ilmnajot.voltu.model.request.AuthDTO;
import uz.ilmnajot.voltu.model.request.LoginDTO;
import uz.ilmnajot.voltu.model.request.VerifyDTO;
import uz.ilmnajot.voltu.repository.CodeRepository;
import uz.ilmnajot.voltu.repository.TokenRepository;
import uz.ilmnajot.voltu.repository.UserRepository;
import uz.ilmnajot.voltu.service.CodeService;
import uz.ilmnajot.voltu.service.SmsService;
import uz.ilmnajot.voltu.service.TokenService;
import uz.ilmnajot.voltu.service.TrustedDeviceService;
import uz.ilmnajot.voltu.utils.ApiMessage;
import uz.ilmnajot.voltu.utils.Utils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final Utils utils;
    private final SmsService smsService;
    private final UserMapper userMapper;
    private final TrustedDeviceService trustedDeviceService;
    private final UserDetailsService userDetailsService;
    private final JwtProvider jwtProvider;
    private final CodeRepository codeRepository;
    private final TokenRepository tokenRepository;
    private final CodeService codeService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(
            UserRepository userRepository,
            @Lazy Utils utils, SmsService smsService,
            UserMapper userMapper,
            TrustedDeviceService trustedDevice,
            UserDetailsService userDetailsService,
            JwtProvider jwtProvider,
            CodeRepository codeRepository,
            TokenRepository tokenRepository,
            CodeService codeService,
            PasswordEncoder passwordEncoder,
            TokenService tokenService,
            AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.utils = utils;
        this.smsService = smsService;
        this.userMapper = userMapper;
        this.trustedDeviceService = trustedDevice;
        this.userDetailsService = userDetailsService;
        this.jwtProvider = jwtProvider;
        this.codeRepository = codeRepository;
        this.tokenRepository = tokenRepository;
        this.codeService = codeService;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public ApiResponse register(AuthDTO authDTO) {
        Optional<User> optionalUser = userRepository.findByPhoneAndDeletedFalse(authDTO.getPhone());
        if (optionalUser.isPresent()) {
            return ApiResponse.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message(ApiMessage.PHONE_NUMBER_ALREADY_EXISTS)
                    .build();
        }
        if (!utils.validatePhoneNumber(authDTO.getPhone())) {
            return ApiResponse.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message("invalid phone number")
                    .build();
        }
        String generatedCode = utils.getCode();
        if (smsService.sendSmsMessage(authDTO.getPhone(), generatedCode)) {
            try {
                User user = this.userMapper.toUserEntity(authDTO);
                user.setCreatedAt(LocalDateTime.now());
                user.setUserRole(UserRole.USER);
                user = userRepository.save(user);
                this.trustedDeviceService.addTrustedDevice(user.getId(), authDTO.getDeviceName());
                UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
                String token = jwtProvider.generateAccessToken(userDetails);


                Code code = new Code();
                code.setUserId(user.getId());
                code.setCode(generatedCode);
                code.setCreatedAt(LocalDateTime.now());
                this.codeRepository.save(code);


                Token jwt = new Token();
                jwt.setToken(token);
                jwt.setUserId(user.getId());
                jwt.setType("Bearer");
                jwt.setCreatedAt(LocalDateTime.now());
                this.tokenRepository.save(jwt);

                return ApiResponse.builder()
                        .httpStatus(HttpStatus.OK)
                        .message("5-digit code has been sent successfully")
                        .build();
            } catch (Exception e) {
                return ApiResponse.builder()
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .message("Something went wrong")
                        .build();
            }
        }

        return ApiResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message("Something went wrong with sending code to the phone number: " + authDTO.getPhone())
                .build();
    }

    @Override
    public ApiResponse verifyBySms(VerifyDTO verifyDTO) {
        User user = getUserByPhone(verifyDTO.getPhoneNumber());
        Map<String, Object> map = new HashMap<>();
        Code codeByUserId = this.codeService.findCodeByUserId(user.getId());
        if (codeByUserId != null && codeByUserId.getCode().equals(verifyDTO.getCode())) {
            user.setPassword(passwordEncoder.encode(verifyDTO.getPassword()));
            this.userRepository.save(user);
            Token token = this.tokenService.getTokenByUserId(user.getId());
            map.put("Token", token != null ? token.getToken() : null);
            map.put("UserId", user.getId());
            map.put("User: ", this.userMapper.toAuthDTO(user));
            return ApiResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("User successfully has been verified")
                    .data(map)
                    .build();
        }

        return ApiResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message("Failed: No code or error with code found!")
                .build();
    }

    @Override
    public ApiResponse login(LoginDTO loginDTO) {
        User user = this.getUserByPhone(loginDTO.getPhoneNumber());
        if (!utils.validatePhoneNumber(user.getPhone())) {
            return ApiResponse.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message("Failed with Phone number you entered: " + loginDTO.getPhoneNumber())
                    .build();
        }
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDTO.getPhoneNumber(),
                    loginDTO.getPassword()
            ));
        } catch (Exception e) {
            return ApiResponse.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message("Invalid password or phone number")
                    .build();
        }
        Token token = this.tokenService.getTokenByUserId(user.getId());
        String userToken = null;
        if (token != null) {
            userToken = token.getToken();
        }
        this.tokenRepository.save(token);
        TrustedDevice trustedDevice = this.trustedDeviceService.findDeviceByUserIdAndDeviceName(user.getId(), loginDTO.getDeviceName());
        String code = utils.getCode();
        if (trustedDevice == null) {
            if (smsService.sendSmsMessage(loginDTO.getPhoneNumber(), code)) {
                User savedUser = this.userRepository.save(user);
                this.trustedDeviceService.addTrustedDevice(savedUser.getId(), loginDTO.getDeviceName());
                Code codeByUserId = this.codeService.findCodeByUserId(savedUser.getId());
                codeByUserId.setCode(code);
                this.codeRepository.save(codeByUserId);
            }
        }
        return ApiResponse.builder()
                .httpStatus(HttpStatus.CREATED)
                .message("SUCCESS")
                .data(userToken)
                .build();

    }

    @Override
    public User getUserByPhone(String phone) {
        Optional<User> optionalUser = this.userRepository.findByPhoneAndDeletedFalse(phone);
        return optionalUser.orElseThrow(() -> new UsernameNotFoundException(ApiMessage.USER_NOT_FOUND));
    }
}
