package uz.ilmnajot.voltu.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import uz.ilmnajot.voltu.entity.User;
import uz.ilmnajot.voltu.enums.UserRole;
import uz.ilmnajot.voltu.model.common.ApiResponse;
import uz.ilmnajot.voltu.model.request.UserStatisticsDTO;

public interface UserService {



    User getUserById(Long userId);
    User findByPhone(String phone);

    ApiResponse getAllUsers(int page, int size);

    ApiResponse getUserByPhoneNumber(String phoneNumber);

    ApiResponse updatePassword(String phoneNumber, String newPassword);

    ApiResponse updatePhone(String oldNumber, String newPhone);

    ApiResponse changeRole(Long userId, UserRole userRole);

    ApiResponse getAllUsersByRole(UserRole userRole, int page, int size);

    ApiResponse logout(String phone);

    ApiResponse blockUser(Long userId);

    ApiResponse unblockUser(Long userId);

    ApiResponse resendCode(String phone);

    UserStatisticsDTO getUserStatistics(Long userId);
}
