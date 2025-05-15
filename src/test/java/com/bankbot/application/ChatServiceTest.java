//package com.bankbot.application;
//
//import com.bankbot.application.dto.ChatMessageDto;
//import com.bankbot.application.dto.TransactionDto;
//import com.bankbot.application.dto.UserMessageDto;
//import com.bankbot.application.port.ChatMessageRepository;
//import com.bankbot.application.port.AiChatClient;
//import com.bankbot.application.service.ChatService;
//import com.bankbot.application.service.TransactionService;
//import com.bankbot.domain.model.ChatMessage;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class ChatServiceTest {
//
//    @Mock
//    private ChatMessageRepository chatMessageRepository;
//
//    @Mock
//    private AiChatClient openAiService;
//
//    @Mock
//    private TransactionService transactionService;
//
//    @InjectMocks
//    private ChatService chatService;
//
//    private UserMessageDto userMessageDto;
//    private List<TransactionDto> transactions;
//    private List<ChatMessage> chatMessages;
//
//    @BeforeEach
//    void setUp() {
//        // Setup test data
//        userMessageDto = UserMessageDto.builder()
//                .userId("user123")
//                .content("How much did I spend last month?")
//                .build();
//
//        transactions = Arrays.asList(
//                TransactionDto.builder()
//                        .id(1L)
//                        .amount(new BigDecimal("123.45"))
//                        .category("Groceries")
//                        .date("2023-10-15")
//                        .description("Weekly grocery shopping")
//                        .type("EXPENSE")
//                        .build(),
//                TransactionDto.builder()
//                        .id(2L)
//                        .amount(new BigDecimal("75.25"))
//                        .category("Dining")
//                        .date("2023-10-10")
//                        .description("Restaurant dinner")
//                        .type("EXPENSE")
//                        .build()
//        );
//
//        LocalDateTime now = LocalDateTime.now();
//        chatMessages = Arrays.asList(
//                ChatMessage.builder()
//                        .id(1L)
//                        .userId("user123")
//                        .messageType("USER")
//                        .content("How much did I spend last month?")
//                        .timestamp(now.minusMinutes(1))
//                        .build(),
//                ChatMessage.builder()
//                        .id(2L)
//                        .userId("user123")
//                        .messageType("BOT")
//                        .content("Based on your transaction history, you spent $198.70 last month.")
//                        .timestamp(now)
//                        .build()
//        );
//    }
//
//    @Test
//    void testProcessUserMessage() {
//        // Mock dependencies
//        when(transactionService.getRecentTransactions(anyString(), anyInt())).thenReturn(transactions);
//        when(openAiService.generateResponse(anyString())).thenReturn("Based on your transaction history, you spent $198.70 last month.");
//
//        // Call the method under test
//        chatService.processUserMessage(userMessageDto);
//
//        // Verify that ChatMessageRepository.save was called twice (once for user message, once for bot response)
//        verify(chatMessageRepository, times(2)).save(any(ChatMessage.class));
//
//        // Verify that openAiService.generateResponse was called with a prompt containing the transactions
//        ArgumentCaptor<String> promptCaptor = ArgumentCaptor.forClass(String.class);
//        verify(openAiService).generateResponse(promptCaptor.capture());
//        String prompt = promptCaptor.getValue();
//
//        // Check that the prompt contains the user message and transaction data
//        assertNotNull(prompt);
//        assert(prompt.contains(userMessageDto.getContent()));
//        for (TransactionDto transaction : transactions) {
//            assert(prompt.contains(transaction.getAmount().toString()));
//            assert(prompt.contains(transaction.getCategory()));
//        }
//    }
//
//    @Test
//    void testGetChatHistory() {
//        // Mock dependencies
//        when(chatMessageRepository.findTop20ByUserIdOrderByTimestampDesc(anyString())).thenReturn(chatMessages);
//
//        // Call the method under test
//        List<ChatMessageDto> result = chatService.getChatHistory("user123");
//
//        // Verify the repository method was called
//        verify(chatMessageRepository).findTop20ByUserIdOrderByTimestampDesc("user123");
//
//        // Verify results
//        assertEquals(2, result.size());
//        assertEquals("USER", result.get(0).getMessageType());
//        assertEquals("How much did I spend last month?", result.get(0).getContent());
//        assertEquals("BOT", result.get(1).getMessageType());
//        assertEquals("Based on your transaction history, you spent $198.70 last month.", result.get(1).getContent());
//    }
//}
