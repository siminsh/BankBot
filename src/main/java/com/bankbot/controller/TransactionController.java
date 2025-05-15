package com.bankbot.controller;

import com.bankbot.application.dto.TransactionDto;
import com.bankbot.application.service.TransactionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/recent/{userId}")
    public ResponseEntity<List<TransactionDto>> getRecentTransactions(
            @PathVariable String userId,
            @RequestParam(defaultValue = "3") int months) {
        List<TransactionDto> transactions = transactionService.getRecentTransactions(userId, months);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<TransactionDto>> getTransactionsByPeriod(
            @PathVariable String userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<TransactionDto> transactions = transactionService.getTransactionsByPeriod(userId, startDate, endDate);
        return ResponseEntity.ok(transactions);
    }
}

