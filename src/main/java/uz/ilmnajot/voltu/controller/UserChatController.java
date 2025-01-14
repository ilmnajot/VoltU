package uz.ilmnajot.voltu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ilmnajot.voltu.service.UserChatService;

@RestController
@RequestMapping("/api")
public class UserChatController {

    private final UserChatService userChatService;

}
