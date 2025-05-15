package com.bankbot.infrastructure.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.topic.user-message}")
    private String userMessageTopic;

    @Value("${kafka.topic.chatbot-response}")
    private String chatbotResponseTopic;
    
    @Value("${kafka.topic.request-reply}")
    private String requestReplyTopic;
    
    @Value("${kafka.topic.dlt}")
    private String deadLetterTopic;
    
    @Value("${kafka.topic.outbox}")
    private String outboxTopic;

    @Bean
    public NewTopic userMessageTopic() {
        return TopicBuilder.name(userMessageTopic)
                .partitions(3)  // Multiple partitions for scaling
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic chatbotResponseTopic() {
        return TopicBuilder.name(chatbotResponseTopic)
                .partitions(3)  // Multiple partitions for scaling
                .replicas(1)
                .build();
    }
    
    @Bean
    public NewTopic requestReplyTopic() {
        return TopicBuilder.name(requestReplyTopic)
                .partitions(3)
                .replicas(1)
                .build();
    }
    
    @Bean
    public NewTopic deadLetterTopic() {
        return TopicBuilder.name(deadLetterTopic)
                .partitions(1)
                .replicas(1)
                .build();
    }
    
    @Bean
    public NewTopic outboxTopic() {
        return TopicBuilder.name(outboxTopic)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
