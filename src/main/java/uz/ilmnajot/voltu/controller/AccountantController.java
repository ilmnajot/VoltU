package uz.ilmnajot.voltu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ilmnajot.voltu.service.AccountantService;

@RestController
@RequestMapping("/api")
public class AccountantController {

    private final AccountantService accountantService;
}
