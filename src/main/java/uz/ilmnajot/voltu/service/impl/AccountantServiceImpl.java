package uz.ilmnajot.voltu.service.impl;

import org.springframework.stereotype.Service;
import uz.ilmnajot.voltu.repository.AccountantRepository;
import uz.ilmnajot.voltu.service.AccountantService;
@Service
public class AccountantServiceImpl implements AccountantService {

    private final AccountantRepository accountantRepository;

    public AccountantServiceImpl(AccountantRepository accountantRepository) {
        this.accountantRepository = accountantRepository;
    }
}
