package uz.ilmnajot.voltu.service.impl;

import org.springframework.stereotype.Service;
import uz.ilmnajot.voltu.entity.Code;
import uz.ilmnajot.voltu.repository.CodeRepository;
import uz.ilmnajot.voltu.service.CodeService;
@Service
public class CodeServiceImpl implements CodeService {

    private final CodeRepository codeRepository;

    public CodeServiceImpl(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @Override
    public Code findCodeByUserId(Long userId) {
        Code code = codeRepository.findByUserId(userId).orElse(null);
        return code;
    }
}
