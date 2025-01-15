package uz.ilmnajot.voltu.service.impl;

import org.springframework.stereotype.Service;
import uz.ilmnajot.voltu.entity.TrustedDevice;
import uz.ilmnajot.voltu.repository.TrustedDeviceRepository;
import uz.ilmnajot.voltu.service.TrustedDeviceService;

import java.time.LocalDateTime;

@Service
public class TrustedDeviceServiceImpl implements TrustedDeviceService {

    private final TrustedDeviceRepository trustedDeviceRepository;

    public TrustedDeviceServiceImpl(TrustedDeviceRepository trustedDeviceRepository) {
        this.trustedDeviceRepository = trustedDeviceRepository;
    }

    @Override
    public Boolean isDeviceTrusted(Long userId, String deviceId) {
        TrustedDevice trustedDevice = this.trustedDeviceRepository.findByUserIdAndDeviceName(userId, deviceId);
        if (trustedDevice == null) {
            return false;
        }
        if (trustedDevice.getExpiredAt() != null && trustedDevice.getExpiredAt().isBefore(LocalDateTime.now())) {
            trustedDevice.setUserId(null);
            return false;
        }
        return true;
    }

    public void addTrustedDevice(Long userId, String deviceName) {
        TrustedDevice device = new TrustedDevice();
        device.setDeviceName(deviceName);
        device.setAddedAt(LocalDateTime.now());
        device.setUserId(userId);

        device.setExpiredAt(LocalDateTime.now().plusDays(15));
        this.trustedDeviceRepository.save(device);
    }
}
