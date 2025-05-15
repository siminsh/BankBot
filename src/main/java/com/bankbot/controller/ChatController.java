package com.bankbot.controller;

import com.bankbot.application.dto.ChatMessageDto;
import com.bankbot.application.dto.UserMessageDto;
import com.bankbot.application.service.ChatService;
import com.bankbot.infrastructure.kafka.KafkaProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatController {

    private final ChatService chatService;
    private final KafkaProducerService kafkaProducerService;

    public ChatController(ChatService chatService, KafkaProducerService kafkaProducerService) {
        this.chatService = chatService;
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody UserMessageDto message) {
        // Send message to Kafka for processing
        kafkaProducerService.sendUserMessage(message);
        return ResponseEntity.ok("Message received");
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<ChatMessageDto>> getChatHistory(@PathVariable String userId) {
        List<ChatMessageDto> chatHistory = chatService.getChatHistory(userId);
        return ResponseEntity.ok(chatHistory);
    }
}
