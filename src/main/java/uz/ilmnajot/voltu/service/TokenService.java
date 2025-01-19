package uz.ilmnajot.voltu.service;

import uz.ilmnajot.voltu.entity.Token;

public interface TokenService {

    Token getTokenByUserId(Long userId);
}
