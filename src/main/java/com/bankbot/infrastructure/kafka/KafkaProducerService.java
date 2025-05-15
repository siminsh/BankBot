package com.bankbot.infrastructure.kafka;

import com.bankbot.application.dto.UserMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String userMessageTopic;

    public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate,
                               @Value("${kafka.topic.user-message}") String userMessageTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.userMessageTopic = userMessageTopic;
    }

    public void sendUserMessage(UserMessageDto message) {
        logger.info("Sending user message to Kafka: {}", message);
        kafkaTemplate.send(userMessageTopic, message.getUserId(), message);
    }
}
