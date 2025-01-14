package uz.ilmnajot.voltu.service.impl;

import org.springframework.stereotype.Service;
import uz.ilmnajot.voltu.entity.User;
import uz.ilmnajot.voltu.repository.UserRepository;
import uz.ilmnajot.voltu.service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User findByPhone(String phone) {
        Optional<User> optionalUser = userRepository.findByPhoneAndDeletedFalse(phone);
        return optionalUser.orElse(null);
    }
}
