package uz.ilmnajot.voltu.mapper;

import uz.ilmnajot.voltu.entity.User;
import uz.ilmnajot.voltu.model.request.AuthDTO;

public interface UserMapper {

    User toUserEntity(AuthDTO authDTO);
}
