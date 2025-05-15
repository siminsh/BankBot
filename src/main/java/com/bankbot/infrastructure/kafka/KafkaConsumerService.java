package com.bankbot.infrastructure.kafka;

import com.bankbot.application.dto.UserMessageDto;
import com.bankbot.application.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    private final ChatService chatService;

    public KafkaConsumerService(ChatService chatService) {
        this.chatService = chatService;
    }

    @KafkaListener(topics = "${kafka.topic.user-message}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeUserMessage(UserMessageDto message) {
        logger.info("Received user message from Kafka: {}", message);
        chatService.processUserMessage(message);
    }
}
