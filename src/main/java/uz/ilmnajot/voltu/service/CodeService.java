package uz.ilmnajot.voltu.service;

import uz.ilmnajot.voltu.entity.Code;

public interface CodeService {

    Code findCodeByUserId(Long userId);
}
