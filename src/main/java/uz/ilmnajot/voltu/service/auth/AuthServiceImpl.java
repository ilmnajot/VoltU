package uz.ilmnajot.voltu.service.auth;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.ilmnajot.voltu.entity.Code;
import uz.ilmnajot.voltu.entity.Token;
import uz.ilmnajot.voltu.entity.User;
import uz.ilmnajot.voltu.enums.UserRole;
import uz.ilmnajot.voltu.jwt.JwtProvider;
import uz.ilmnajot.voltu.mapper.UserMapper;
import uz.ilmnajot.voltu.model.common.ApiResponse;
import uz.ilmnajot.voltu.model.request.AuthDTO;
import uz.ilmnajot.voltu.repository.CodeRepository;
import uz.ilmnajot.voltu.repository.TokenRepository;
import uz.ilmnajot.voltu.repository.UserRepository;
import uz.ilmnajot.voltu.service.SmsService;
import uz.ilmnajot.voltu.service.TrustedDeviceService;
import uz.ilmnajot.voltu.utils.ApiMessage;
import uz.ilmnajot.voltu.utils.Utils;

import java.time.LocalDateTime;
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

    public AuthServiceImpl(UserRepository userRepository, Utils utils, SmsService smsService, UserMapper userMapper, TrustedDeviceService trustedDevice, UserDetailsService userDetailsService, JwtProvider jwtProvider, CodeRepository codeRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.utils = utils;
        this.smsService = smsService;
        this.userMapper = userMapper;
        this.trustedDeviceService = trustedDevice;
        this.userDetailsService = userDetailsService;
        this.jwtProvider = jwtProvider;
        this.codeRepository = codeRepository;
        this.tokenRepository = tokenRepository;
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
                String token = jwtProvider.generateToken(userDetails.getUsername());


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
    public User getUserByPhone(String phone) {
        Optional<User> optionalUser = userRepository.findByPhoneAndDeletedFalse(phone);
        return optionalUser.orElseThrow(() -> new UsernameNotFoundException(ApiMessage.USER_NOT_FOUND));
    }
}
