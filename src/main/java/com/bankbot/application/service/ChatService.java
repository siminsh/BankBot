package com.bankbot.application.service;

import com.bankbot.application.dto.ChatMessageDto;
import com.bankbot.application.dto.TransactionDto;
import com.bankbot.application.dto.UserMessageDto;
import com.bankbot.application.port.AiChatClient;
import com.bankbot.application.port.ChatMessageRepository;
import com.bankbot.domain.model.ChatMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;
    private final AiChatClient openAiService;
    private final TransactionService transactionService;

    public ChatService(ChatMessageRepository chatMessageRepository, 
                      AiChatClient openAiService,
                      TransactionService transactionService) {
        this.chatMessageRepository = chatMessageRepository;
        this.openAiService = openAiService;
        this.transactionService = transactionService;
    }

    public void processUserMessage(UserMessageDto userMessageDto) {
        // Save user message to database
        ChatMessage userChatMessage = ChatMessage.builder()
                .userId(userMessageDto.getUserId())
                .messageType("USER")
                .content(userMessageDto.getContent())
                .timestamp(LocalDateTime.now())
                .build();
        
        chatMessageRepository.save(userChatMessage);
        
        // Fetch recent transactions for context
        List<TransactionDto> recentTransactions = transactionService.getRecentTransactions(userMessageDto.getUserId(), 3);
        
        // Create prompt for OpenAI
        String prompt = createPrompt(userMessageDto.getContent(), recentTransactions);
        
        // Get response from OpenAI
        String botResponse = openAiService.generateResponse(prompt);
        
        // Save bot response to database
        ChatMessage botChatMessage = ChatMessage.builder()
                .userId(userMessageDto.getUserId())
                .messageType("BOT")
                .content(botResponse)
                .timestamp(LocalDateTime.now())
                .build();
        
        chatMessageRepository.save(botChatMessage);
    }

    public List<ChatMessageDto> getChatHistory(String userId) {
        List<ChatMessage> chatMessages = chatMessageRepository.findTop20ByUserIdOrderByTimestampDesc(userId);
        
        return chatMessages.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private String createPrompt(String userMessage, List<TransactionDto> transactions) {
        StringBuilder promptBuilder = new StringBuilder();
        promptBuilder.append("You are an AI banking assistant analyzing financial data. ");
        promptBuilder.append("Please provide a helpful answer to the following user query: \"");
        promptBuilder.append(userMessage);
        promptBuilder.append("\"\n\nHere are the user's recent transactions:\n");
        
        for (TransactionDto transaction : transactions) {
            promptBuilder.append("- Date: ").append(transaction.getDate());
            promptBuilder.append(", Amount: ").append(transaction.getAmount());
            promptBuilder.append(", Category: ").append(transaction.getCategory());
            promptBuilder.append(", Type: ").append(transaction.getType());
            promptBuilder.append(", Description: ").append(transaction.getDescription()).append("\n");
        }
        
        promptBuilder.append("\nPlease analyze the data and provide a concise, helpful response to the user's question.");
        return promptBuilder.toString();
    }

    private ChatMessageDto mapToDto(ChatMessage chatMessage) {
        return ChatMessageDto.builder()
                .id(chatMessage.getId())
                .userId(chatMessage.getUserId())
                .messageType(chatMessage.getMessageType())
                .content(chatMessage.getContent())
                .timestamp(chatMessage.getTimestamp().format(DateTimeFormatter.ISO_DATE_TIME))
                .build();
    }
}
