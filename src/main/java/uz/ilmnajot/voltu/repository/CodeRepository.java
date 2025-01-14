package uz.ilmnajot.voltu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.voltu.entity.Code;

import java.util.Optional;

@Repository
public interface CodeRepository extends JpaRepository<Code, Long> {
    Optional<Code> findByUserId(Long userId);
}
