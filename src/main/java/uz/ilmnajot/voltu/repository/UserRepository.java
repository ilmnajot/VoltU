package uz.ilmnajot.voltu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.voltu.entity.User;
import uz.ilmnajot.voltu.enums.UserRole;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhoneAndDeletedFalse(String phone);

    @Query(value = "select * from users as u where u.userRole=:userRole ORDER BY u.id limit :size OFFSET :offset", nativeQuery = true)
    List<User> findAllByRole(@Param("userRole")String userRole,
                             @Param("offset") Integer offset,
                             @Param("size") Integer size);
}
