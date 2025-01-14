package uz.ilmnajot.voltu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.voltu.entity.UserChat;

@Repository
public interface UserChatRepository extends JpaRepository<UserChat, Long> {
}
