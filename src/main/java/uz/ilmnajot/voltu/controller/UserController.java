package uz.ilmnajot.voltu.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.voltu.enums.UserRole;
import uz.ilmnajot.voltu.model.common.ApiResponse;
import uz.ilmnajot.voltu.model.request.UserStatisticsDTO;
import uz.ilmnajot.voltu.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUsers")
    public ApiResponse getAllUsers(@RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(name = "size", defaultValue = "20") int size) {
        return this.userService.getAllUsers(page, size);
    }

    @GetMapping("/getUserByPhoneNumber")
    public ApiResponse getUserByPhone(@RequestParam(name = "PhoneNumber") String phoneNumber) {
        return this.userService.getUserByPhoneNumber(phoneNumber);
    }

    @PutMapping("/updatePassowrd")
    public ApiResponse updatePassword(@RequestParam(name = "phoneNumber") String phoneNumber,
                                      @RequestParam(name = "newPassword") String newPassword) {
        return this.userService.updatePassword(phoneNumber, newPassword);
    }

    @PutMapping("updatePhone")
    public ApiResponse updatePhoneNumber(@RequestParam(name = "oldNumber") String oldNumber,
                                         @RequestParam(name = "newPhone") String newPhone) {
        return this.userService.updatePhone(oldNumber, newPhone);
    }

    @PutMapping("/updateRole")
    public ApiResponse updateRole(@RequestParam(name = "userId") Long userId,
                                  @RequestParam(name = "userRole") UserRole userRole) {
        return this.userService.changeRole(userId, userRole);
    }

    @GetMapping("/getAllUsersByRole")
    public ApiResponse getALlUsersByRole(@RequestParam(name = "userRole") UserRole userRole,
                                         @RequestParam(name = "page", defaultValue = "0") int page,
                                         @RequestParam(name = "size", defaultValue = "20") int size) {
        return this.userService.getAllUsersByRole(userRole, page, size);
    }

    @DeleteMapping("/logout")
    public ApiResponse logout(@RequestParam(name = "phone") String phone) {
        return this.userService.logout(phone);
    }

    @PostMapping("/blockUser")
    public ApiResponse blockUser(@RequestParam("userId") Long userId) {
        return this.userService.blockUser(userId);
    }

    @PostMapping("unblockUser")
    public ApiResponse unblockUser(@RequestParam("userId") Long userId) {
        return this.userService.unblockUser(userId);
    }

    @PostMapping("resendCode")
    public ApiResponse resendCode(@RequestParam("phone") String phone) {
        return this.userService.resendCode(phone);
    }

    public UserStatisticsDTO getStatistics(@RequestParam(name = "userId") Long userId) {
        return this.userService.getUserStatistics(userId);
    }

}
