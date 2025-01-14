package uz.ilmnajot.voltu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.voltu.entity.Accountant;

@Repository
public interface AccountantRepository extends JpaRepository<Accountant, Long> {
}
