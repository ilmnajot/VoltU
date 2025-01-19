package uz.ilmnajot.voltu.service.impl;

import org.springframework.stereotype.Service;
import uz.ilmnajot.voltu.entity.Token;
import uz.ilmnajot.voltu.exception.ResourceNotFoundException;
import uz.ilmnajot.voltu.repository.TokenRepository;
import uz.ilmnajot.voltu.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Token getTokenByUserId(Long userId) {
        return this.tokenRepository.findByUserId(userId).orElseThrow(
                () -> new ResourceNotFoundException("Token not found"));
    }
}
