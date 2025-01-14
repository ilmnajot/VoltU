package uz.ilmnajot.voltu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.voltu.entity.Transactions;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {
}
