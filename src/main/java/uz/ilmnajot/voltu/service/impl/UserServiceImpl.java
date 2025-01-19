package uz.ilmnajot.voltu.service.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.ilmnajot.voltu.entity.Code;
import uz.ilmnajot.voltu.entity.Token;
import uz.ilmnajot.voltu.entity.TrustedDevice;
import uz.ilmnajot.voltu.entity.User;
import uz.ilmnajot.voltu.enums.UserRole;
import uz.ilmnajot.voltu.exception.ResourceNotFoundException;
import uz.ilmnajot.voltu.mapper.UserMapper;
import uz.ilmnajot.voltu.model.common.ApiResponse;
import uz.ilmnajot.voltu.model.request.UserStatisticsDTO;
import uz.ilmnajot.voltu.model.response.UserResponse;
import uz.ilmnajot.voltu.repository.*;
import uz.ilmnajot.voltu.service.CodeService;
import uz.ilmnajot.voltu.service.SmsService;
import uz.ilmnajot.voltu.service.UserService;
import uz.ilmnajot.voltu.service.auth.AuthService;
import uz.ilmnajot.voltu.utils.ApiMessage;
import uz.ilmnajot.voltu.utils.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthService authService;
    private final Utils utils;
    private final SmsService smsService;
    private final PasswordEncoder passwordEncoder;
    private final CodeService codeService;
    private final CodeRepository codeRepository;
    private final TokenRepository tokenRepository;
    private final TrustedDeviceRepository trustedDeviceRepository;
    private final ChargingSessionRepository chargingSessionRepository;


    public UserServiceImpl(
            UserRepository userRepository,
            UserMapper userMapper,
            AuthService authService,
           @Lazy Utils utils,
            SmsService smsService,
            PasswordEncoder passwordEncoder,
            CodeService codeService,
            CodeRepository codeRepository,
            TokenRepository tokenRepository,
            TrustedDeviceRepository trustedDeviceRepository,
            ChargingSessionRepository chargingSessionRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.authService = authService;
        this.utils = utils;
        this.smsService = smsService;
        this.passwordEncoder = passwordEncoder;
        this.codeService = codeService;
        this.codeRepository = codeRepository;
        this.tokenRepository = tokenRepository;
        this.trustedDeviceRepository = trustedDeviceRepository;
        this.chargingSessionRepository = chargingSessionRepository;
    }


    @Override
    public User findByPhone(String phone) {
        Optional<User> optionalUser = userRepository.findByPhoneAndDeletedFalse(phone);
        return optionalUser.orElse(null);
    }

    @Override
    public ApiResponse getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> usersPage = this.userRepository.findAll(pageable);

        Map<String, Object> map = new HashMap<>();
        map.put("Total Pages", usersPage.getTotalPages());
        map.put("Total Elements: ", usersPage.getTotalElements());
        map.put("Users: ", this.userMapper.toUserDTOList(usersPage.getContent()));
        return ApiResponse.builder()
                .httpStatus(HttpStatus.CREATED)
                .message("SUCCESS")
                .data(map)
                .build();
    }

    @Override
    public ApiResponse getUserByPhoneNumber(String phoneNumber) {
        User user = this.userRepository.findByPhoneAndDeletedFalse(phoneNumber).orElseThrow(
                () -> new ResourceNotFoundException("User not found with Phone Number: " + phoneNumber));
        UserResponse userResponse = this.userMapper.toUserResponse(user);
        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(ApiMessage.SUCCESS)
                .data(userResponse)
                .build();
    }

    @Override
    public ApiResponse updatePassword(String phoneNumber, String newPassword) {
        User user = this.authService.getUserByPhone(phoneNumber);
        String code = utils.getCode();
        if (this.smsService.sendSmsMessage(phoneNumber, code)) {
            user.setPassword(passwordEncoder.encode(newPassword));
            Code codeByUserId = this.codeService.findCodeByUserId(user.getId());
            codeByUserId.setCode(code);
            this.codeRepository.save(codeByUserId);
            this.userRepository.save(user);
            return ApiResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message(ApiMessage.SUCCESS)
                    .build();
        }
        return null;
    }

    @Override
    public ApiResponse updatePhone(String oldNumber, String newPhone) {
        Optional<User> optionalUser = this.userRepository.findByPhoneAndDeletedFalse(newPhone);
        if (optionalUser.isPresent()) {
            return ApiResponse.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message(ApiMessage.PHONE_NUMBER_ALREADY_EXISTS)
                    .build();
        }
        User user = this.authService.getUserByPhone(oldNumber);
        String code = utils.getCode();
        user.setPhone(newPhone);
        if (smsService.sendSmsMessage(newPhone, code)) {
            Code codeByUserId = this.codeService.findCodeByUserId(user.getId());
            codeByUserId.setCode(code);
            this.codeRepository.save(codeByUserId);
            this.userRepository.save(user);
            return ApiResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("ENTER CODE")
                    .build();
        }
        return ApiResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message("ERROR WITH SENDING WITH CODE")
                .build();
    }

    @Override
    public ApiResponse changeRole(Long userId, UserRole userRole) {
        User user = this.getUserById(userId);
        user.setUserRole(userRole);
        this.userRepository.save(user);
        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Role has been changed successfully")
                .build();
    }

    @Override
    public ApiResponse getAllUsersByRole(UserRole userRole, int page, int size) {
        Integer offset = (page - 1) * size;
        List<User> userList = this.userRepository.findAllByRole(userRole.name(), offset, size);
        if (userList != null && userList.isEmpty()) {
            return ApiResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message(ApiMessage.SUCCESS)
                    .data(this.userMapper.toUserDTOList(userList))
                    .build();
        }
        return ApiResponse.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message("Failed to fetch users by Role")
                .build();
    }

    @Override
    public ApiResponse logout(String phone) {
        User user = this.authService.getUserByPhone(phone);
        Optional<Token> token = this.tokenRepository.findByUserId(user.getId());
        token.ifPresent(this.tokenRepository::delete);

        Code code = this.codeService.findCodeByUserId(user.getId());
        this.codeRepository.delete(code);

        Optional<TrustedDevice> trustedDevice = this.trustedDeviceRepository.findByUserId(user.getId());
        trustedDevice.ifPresent(this.trustedDeviceRepository::delete);
        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("LOGOUT SUCCESSFULLY")
                .build();
    }

    @Override
    public ApiResponse blockUser(Long userId) {
        User user = this.getUserById(userId);
        user.setBlocked(true);
        this.userRepository.save(user);
        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("The user has been blocked")
                .build();
    }

    @Override
    public ApiResponse unblockUser(Long userId) {
        User user = this.getUserById(userId);
        user.setBlocked(false);
        this.userRepository.save(user);
        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("The User has been unblocked successfully")
                .build();
    }

    @Override
    public ApiResponse resendCode(String phone) {
        User user = this.authService.getUserByPhone(phone);
        String code = this.utils.getCode();
        Code codeByUserId = this.codeService.findCodeByUserId(user.getId());
        if (this.smsService.sendSmsMessage(phone, code)) {
            codeByUserId.setCode(code);
            this.codeRepository.save(codeByUserId);
        }
        return ApiResponse.builder()
                .httpStatus(HttpStatus.OK)
                .success(true)
                .message("CODE HAS BEEN SENT SUCCESSFULLY")
                .build();
    }

    @Override
    public UserStatisticsDTO getUserStatistics(Long userId) {
        this.getUserById(userId);
        Double totalSpentByUserId = this.chargingSessionRepository.getTotalSpentByUserId(userId);
        Double totalChargedByUserId = this.chargingSessionRepository.getTotalChargedByUserId(userId);
        Double chargingTotalTimeInMinutes = this.chargingSessionRepository.getChargingTotalTime(userId);
        String totalChargingTime = chargingTotalTimeInMinutes != null
                ? String.format("%02d:%02d",
                (int) (chargingTotalTimeInMinutes / 60),
                (int) (chargingTotalTimeInMinutes % 60))
                : "00:00";
        List<Long> visitedStationsByUserId = this.chargingSessionRepository.getVisitedStationsByUserId(userId);
        Double totalParkingTimeByUserId = this.chargingSessionRepository.totalParkingTimeByUserId(userId);
        String totalParkingTime = totalParkingTimeByUserId != null
                ? String.format("$02d:%02d",
                (int) (totalParkingTimeByUserId / 60),
                (int) (totalParkingTimeByUserId % 60))
                : "00:00";
        Integer totalCyclesByUser = this.chargingSessionRepository.getTotalCyclesByUser(userId);
        UserStatisticsDTO statisticsDTO = new UserStatisticsDTO();
        statisticsDTO.setTotalSpent(totalSpentByUserId);
        statisticsDTO.setTotalCharged(totalChargedByUserId);
        statisticsDTO.setTotalSpentTime(totalChargingTime);
        statisticsDTO.setTotalParkingTime(totalParkingTime);
        statisticsDTO.setVisitedStations(visitedStationsByUserId);
        statisticsDTO.setCyclesCounts(totalCyclesByUser);
        return statisticsDTO;
    }

    public User getUserById(Long userId) {
        return this.userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException(ApiMessage.USER_NOT_FOUND));
    }
}
