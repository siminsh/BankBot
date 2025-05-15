package com.bankbot.infrastructure.kafka.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Base class for Kafka messages with correlation ID support for request-reply pattern
 * and other fields needed for exactly-once processing and deduplication.
 */
public class KafkaMessage<T> implements Serializable {
    
    private UUID messageId;
    private UUID correlationId;
    private LocalDateTime timestamp;
    private int partitionKey;
    private T payload;
    private int retryCount;
    private String messageType;

    public KafkaMessage() {
        this.messageId = UUID.randomUUID();
        this.timestamp = LocalDateTime.now();
        this.retryCount = 0;
    }
    
    public KafkaMessage(T payload) {
        this();
        this.correlationId = UUID.randomUUID();
        this.payload = payload;
    }
    
    public KafkaMessage(T payload, UUID correlationId) {
        this();
        this.correlationId = correlationId;
        this.payload = payload;
    }

    // Getters and Setters
    public UUID getMessageId() {
        return messageId;
    }

    public void setMessageId(UUID messageId) {
        this.messageId = messageId;
    }

    public UUID getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(UUID correlationId) {
        this.correlationId = correlationId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getPartitionKey() {
        return partitionKey;
    }

    public void setPartitionKey(int partitionKey) {
        this.partitionKey = partitionKey;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public void incrementRetryCount() {
        this.retryCount++;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    @Override
    public String toString() {
        return "KafkaMessage{" +
                "messageId=" + messageId +
                ", correlationId=" + correlationId +
                ", timestamp=" + timestamp +
                ", partitionKey=" + partitionKey +
                ", payload=" + payload +
                ", retryCount=" + retryCount +
                ", messageType='" + messageType + '\'' +
                '}';
    }
}
