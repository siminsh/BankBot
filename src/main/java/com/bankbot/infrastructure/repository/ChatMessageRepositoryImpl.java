package com.bankbot.infrastructure.repository;

import com.bankbot.application.port.ChatMessageRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepositoryImpl extends ChatMessageRepository {
    // Spring Data JPA will automatically implement the methods defined in ChatMessageRepository
}
