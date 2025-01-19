package uz.ilmnajot.voltu.mapper;

import uz.ilmnajot.voltu.entity.User;
import uz.ilmnajot.voltu.model.request.AuthDTO;
import uz.ilmnajot.voltu.model.response.UserResponse;

import java.util.List;

public interface UserMapper {

    User toUserEntity(AuthDTO authDTO);
    AuthDTO toAuthDTO(User user);
    List<UserResponse> toUserDTOList(List<User> users);
    UserResponse toUserResponse(User user);
}
