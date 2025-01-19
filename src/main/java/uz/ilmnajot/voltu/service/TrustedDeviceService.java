package uz.ilmnajot.voltu.service;

import uz.ilmnajot.voltu.entity.TrustedDevice;

public interface TrustedDeviceService {

    Boolean isDeviceTrusted(Long userId, String deviceId);
    void addTrustedDevice(Long userId, String deviceName);
    TrustedDevice findDeviceByUserIdAndDeviceName(Long userId, String deviceName);
}
