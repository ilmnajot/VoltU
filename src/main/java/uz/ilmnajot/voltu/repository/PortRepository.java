package uz.ilmnajot.voltu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.voltu.entity.Port;

@Repository
public interface PortRepository extends JpaRepository<Port, Long> {
}
