package uz.ilmnajot.voltu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.voltu.entity.SupportChat;

@Repository
public interface SupportChatRepository extends JpaRepository<SupportChat, Long> {
}
