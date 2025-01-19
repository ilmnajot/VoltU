package uz.ilmnajot.voltu.mapper;

import org.springframework.stereotype.Component;
import uz.ilmnajot.voltu.entity.User;
import uz.ilmnajot.voltu.model.request.AuthDTO;
import uz.ilmnajot.voltu.model.response.UserResponse;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public User toUserEntity(AuthDTO authDTO) {
        User user = new User();
        user.setFullName(authDTO.getFullName());
        user.setUsername(authDTO.getUsername());
        user.setEmail(authDTO.getEmail());
        user.setPhone(authDTO.getPhone());
        user.setBlocked(false);
        return user;
    }

    public AuthDTO toAuthDTO(User user) {
        AuthDTO authDTO = new AuthDTO();
        authDTO.setFullName(user.getFullName());
        authDTO.setUsername(user.getUsername());
        authDTO.setEmail(user.getEmail());
        authDTO.setPhone(user.getPhone());
        return authDTO;
    }

    @Override
    public UserResponse toUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setFullName(user.getFullName());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setBalance(user.getBalance());
        response.setUserRole(user.getUserRole());
        response.setBlocked(user.isBlocked());
        response.setChatId(user.getChatId());
        return response;
    }

    @Override
    public List<UserResponse> toUserDTOList(List<User> users) {
        if (users != null && users.isEmpty()) {
            return users.stream().map(this::toUserResponse).toList();
        }
        return new ArrayList<>();
    }
}
