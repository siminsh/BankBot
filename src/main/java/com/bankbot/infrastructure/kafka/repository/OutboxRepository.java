package com.bankbot.infrastructure.kafka.repository;

import com.bankbot.infrastructure.kafka.model.OutboxMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OutboxRepository extends JpaRepository<OutboxMessage, Long> {
    
    List<OutboxMessage> findByProcessedOrderByCreatedAtAsc(boolean processed);
    
    @Query("SELECT o FROM OutboxMessage o WHERE o.processed = false ORDER BY o.createdAt ASC LIMIT :limit")
    List<OutboxMessage> findUnprocessedMessages(int limit);
    
    boolean existsByEventId(UUID eventId);
}
