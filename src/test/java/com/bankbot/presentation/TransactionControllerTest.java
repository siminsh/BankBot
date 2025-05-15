//package com.bankbot.presentation;
//
//import com.bankbot.application.dto.TransactionDto;
//import com.bankbot.application.service.TransactionService;
//import com.bankbot.presentation.controller.TransactionController;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(TransactionController.class)
//public class TransactionControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private TransactionService transactionService;
//
//    @Test
//    public void testGetRecentTransactions() throws Exception {
//        // Prepare test data
//        String userId = "user123";
//        int months = 3;
//        List<TransactionDto> transactions = Arrays.asList(
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
//                        .amount(new BigDecimal("1500.00"))
//                        .category("Salary")
//                        .date("2023-10-01")
//                        .description("Monthly salary")
//                        .type("INCOME")
//                        .build()
//        );
//
//        // Mock the service behavior
//        when(transactionService.getRecentTransactions(userId, months)).thenReturn(transactions);
//
//        // Perform the test
//        mockMvc.perform(get("/api/transactions/recent/{userId}", userId)
//                .param("months", String.valueOf(months)))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$[0].id").value(1))
//                .andExpect(jsonPath("$[0].amount").value(123.45))
//                .andExpect(jsonPath("$[0].category").value("Groceries"))
//                .andExpect(jsonPath("$[0].date").value("2023-10-15"))
//                .andExpect(jsonPath("$[0].type").value("EXPENSE"))
//                .andExpect(jsonPath("$[1].id").value(2))
//                .andExpect(jsonPath("$[1].amount").value(1500.00))
//                .andExpect(jsonPath("$[1].category").value("Salary"))
//                .andExpect(jsonPath("$[1].type").value("INCOME"));
//
//        // Verify that the service was called
//        verify(transactionService, times(1)).getRecentTransactions(userId, months);
//    }
//
//    @Test
//    public void testGetTransactionsByPeriod() throws Exception {
//        // Prepare test data
//        String userId = "user123";
//        LocalDate startDate = LocalDate.of(2023, 9, 1);
//        LocalDate endDate = LocalDate.of(2023, 9, 30);
//        List<TransactionDto> transactions = Arrays.asList(
//                TransactionDto.builder()
//                        .id(1L)
//                        .amount(new BigDecimal("75.25"))
//                        .category("Dining")
//                        .date("2023-09-15")
//                        .description("Restaurant dinner")
//                        .type("EXPENSE")
//                        .build(),
//                TransactionDto.builder()
//                        .id(2L)
//                        .amount(new BigDecimal("42.99"))
//                        .category("Entertainment")
//                        .date("2023-09-20")
//                        .description("Movie tickets")
//                        .type("EXPENSE")
//                        .build()
//        );
//
//        // Mock the service behavior
//        when(transactionService.getTransactionsByPeriod(userId, startDate, endDate)).thenReturn(transactions);
//
//        // Perform the test
//        mockMvc.perform(get("/api/transactions/{userId}", userId)
//                .param("startDate", "2023-09-01")
//                .param("endDate", "2023-09-30"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$[0].id").value(1))
//                .andExpect(jsonPath("$[0].amount").value(75.25))
//                .andExpect(jsonPath("$[0].category").value("Dining"))
//                .andExpect(jsonPath("$[0].date").value("2023-09-15"))
//                .andExpect(jsonPath("$[1].id").value(2))
//                .andExpect(jsonPath("$[1].amount").value(42.99))
//                .andExpect(jsonPath("$[1].category").value("Entertainment"));
//
//        // Verify that the service was called
//        verify(transactionService, times(1)).getTransactionsByPeriod(userId, startDate, endDate);
//    }
//}
