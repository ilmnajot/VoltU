package uz.ilmnajot.voltu.service.impl;

import org.springframework.stereotype.Service;
import uz.ilmnajot.voltu.repository.SupportChatRepository;
import uz.ilmnajot.voltu.service.SupportChatService;
@Service
public class SupportChatServiceImpl implements SupportChatService {

    private final SupportChatRepository supportChatRepository;

    public SupportChatServiceImpl(SupportChatRepository supportChatRepository) {
        this.supportChatRepository = supportChatRepository;
    }
}
