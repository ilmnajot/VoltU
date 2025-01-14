package uz.ilmnajot.voltu.service;

import uz.ilmnajot.voltu.entity.User;

public interface UserService {



    User findByPhone(String phone);
}
