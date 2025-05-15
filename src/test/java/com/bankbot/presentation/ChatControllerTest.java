//package com.bankbot.presentation;
//
//import com.bankbot.application.dto.ChatMessageDto;
//import com.bankbot.application.dto.UserMessageDto;
//import com.bankbot.application.service.ChatService;
//import com.bankbot.controller.ChatController;
//import com.bankbot.infrastructure.kafka.KafkaProducerService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(ChatController.class)
//public class ChatControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private ChatService chatService;
//
//    @MockBean
//    private KafkaProducerService kafkaProducerService;
//
//    @Test
//    public void testSendMessage() throws Exception {
//        // Prepare test data
//        UserMessageDto messageDto = UserMessageDto.builder()
//                .userId("user123")
//                .content("How much did I spend last month?")
//                .build();
//
//        // Perform the test
//        mockMvc.perform(post("/api/chat/send")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(messageDto)))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Message received"));
//
//        // Verify that the Kafka producer service was called
//        verify(kafkaProducerService, times(1)).sendUserMessage(messageDto);
//    }
//
//    @Test
//    public void testGetChatHistory() throws Exception {
//        // Prepare test data
//        String userId = "user123";
//        List<ChatMessageDto> chatHistory = Arrays.asList(
//                ChatMessageDto.builder()
//                        .id(1L)
//                        .userId(userId)
//                        .messageType("USER")
//                        .content("How much did I spend last month?")
//                        .timestamp("2023-10-15T10:15:30")
//                        .build(),
//                ChatMessageDto.builder()
//                        .id(2L)
//                        .userId(userId)
//                        .messageType("BOT")
//                        .content("Based on your transaction history, you spent $1,234.56 last month.")
//                        .timestamp("2023-10-15T10:15:35")
//                        .build()
//        );
//
//        // Mock the service behavior
//        when(chatService.getChatHistory(userId)).thenReturn(chatHistory);
//
//        // Perform the test
//        mockMvc.perform(get("/api/chat/history/{userId}", userId))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$[0].id").value(1))
//                .andExpect(jsonPath("$[0].userId").value(userId))
//                .andExpect(jsonPath("$[0].messageType").value("USER"))
//                .andExpect(jsonPath("$[0].content").value("How much did I spend last month?"))
//                .andExpect(jsonPath("$[1].id").value(2))
//                .andExpect(jsonPath("$[1].messageType").value("BOT"));
//
//        // Verify that the service was called
//        verify(chatService, times(1)).getChatHistory(userId);
//    }
//}
