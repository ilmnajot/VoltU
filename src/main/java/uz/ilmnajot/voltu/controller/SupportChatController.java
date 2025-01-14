package uz.ilmnajot.voltu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ilmnajot.voltu.service.SupportChatService;

@RestController
@RequestMapping("/api")
public class SupportChatController {

    private final SupportChatService supportChatService;

}
