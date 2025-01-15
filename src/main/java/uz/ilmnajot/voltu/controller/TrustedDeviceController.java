package uz.ilmnajot.voltu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ilmnajot.voltu.entity.TrustedDevice;
import uz.ilmnajot.voltu.service.TrustedDeviceService;

@RestController
@RequestMapping("/api")
public class TrustedDeviceController {
    private final TrustedDeviceService trustedDeviceService;

    public TrustedDeviceController(TrustedDeviceService trustedDeviceService) {
        this.trustedDeviceService = trustedDeviceService;
    }


}
