package com.bankbot.application.port;

import com.bankbot.domain.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    
    List<ChatMessage> findByUserIdOrderByTimestampDesc(String userId);
    
    List<ChatMessage> findTop20ByUserIdOrderByTimestampDesc(String userId);
}
