package uz.ilmnajot.voltu.service;

public interface TrustedDeviceService {

    Boolean isDeviceTrusted(Long userId, String deviceId);
    void addTrustedDevice(Long userId, String deviceName);
}
