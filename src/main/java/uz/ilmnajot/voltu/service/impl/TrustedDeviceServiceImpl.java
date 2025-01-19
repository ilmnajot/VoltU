package uz.ilmnajot.voltu.service.impl;

import org.springframework.stereotype.Service;
import uz.ilmnajot.voltu.entity.TrustedDevice;
import uz.ilmnajot.voltu.exception.ResourceNotFoundException;
import uz.ilmnajot.voltu.repository.TrustedDeviceRepository;
import uz.ilmnajot.voltu.service.TrustedDeviceService;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TrustedDeviceServiceImpl implements TrustedDeviceService {

    private final TrustedDeviceRepository trustedDeviceRepository;

    public TrustedDeviceServiceImpl(TrustedDeviceRepository trustedDeviceRepository) {
        this.trustedDeviceRepository = trustedDeviceRepository;
    }

    @Override
    public Boolean isDeviceTrusted(Long userId, String deviceId) {
        TrustedDevice device = this.trustedDeviceRepository.findByUserIdAndDeviceName(userId, deviceId).orElseThrow(() -> new ResourceNotFoundException("Device not found"));
        if (device == null) {
            return false;
        }
        if (device.getExpiredAt() != null && device.getExpiredAt().isBefore(LocalDateTime.now())) {
            device.setUserId(null);
            return false;
        }
        return true;
    }

    public void addTrustedDevice(Long userId, String deviceName) {
        TrustedDevice device = new TrustedDevice();
        device.setDeviceName(deviceName);
        device.setAddedAt(LocalDateTime.now());
        device.setUserId(userId);

        device.setExpiredAt(LocalDateTime.now().plusDays(15)); // expire in 15 days | 15 kunda expire bo'ladi!
        this.trustedDeviceRepository.save(device);
    }

    @Override
    public TrustedDevice findDeviceByUserIdAndDeviceName(Long userId, String deviceName) {
        return this.trustedDeviceRepository.findByUserIdAndDeviceName(userId, deviceName).orElseThrow(
                () -> new ResourceNotFoundException("Device not found"));
    }
}
