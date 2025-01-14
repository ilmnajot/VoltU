package uz.ilmnajot.voltu.service.impl;

import org.springframework.stereotype.Service;
import uz.ilmnajot.voltu.repository.TrustedDeviceRepository;
import uz.ilmnajot.voltu.service.TrustedDeviceService;
@Service
public class TrustedDeviceServiceImpl implements TrustedDeviceService {

    private final TrustedDeviceRepository trustedDeviceRepository;

    public TrustedDeviceServiceImpl(TrustedDeviceRepository trustedDeviceRepository) {
        this.trustedDeviceRepository = trustedDeviceRepository;
    }
}
