package uz.ilmnajot.voltu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ilmnajot.voltu.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

}
