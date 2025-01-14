package uz.ilmnajot.voltu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.voltu.entity.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
}
