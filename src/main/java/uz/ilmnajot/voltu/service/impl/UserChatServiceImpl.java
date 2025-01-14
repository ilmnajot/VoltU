package uz.ilmnajot.voltu.service.impl;

import org.springframework.stereotype.Service;
import uz.ilmnajot.voltu.repository.UserChatRepository;
import uz.ilmnajot.voltu.service.UserChatService;
@Service
public class UserChatServiceImpl implements UserChatService {

    private final UserChatRepository userChatRepository;

    public UserChatServiceImpl(UserChatRepository userChatRepository) {
        this.userChatRepository = userChatRepository;
    }
}
