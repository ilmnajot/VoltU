package uz.ilmnajot.voltu.mapper;

import org.springframework.stereotype.Component;
import uz.ilmnajot.voltu.entity.User;
import uz.ilmnajot.voltu.model.request.AuthDTO;

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
}
