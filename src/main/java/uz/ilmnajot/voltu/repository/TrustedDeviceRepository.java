package uz.ilmnajot.voltu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.voltu.entity.TrustedDevice;

@Repository
public interface TrustedDeviceRepository extends JpaRepository<TrustedDevice, Long> {

    TrustedDevice findByUserIdAndDeviceName(Long userId, String deviceName);
}
