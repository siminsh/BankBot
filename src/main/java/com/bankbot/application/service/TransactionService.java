package com.bankbot.application.service;

import com.bankbot.application.dto.TransactionDto;
import com.bankbot.application.port.TransactionRepository;
import com.bankbot.domain.model.TransactionOperation;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<TransactionDto> getRecentTransactions(String userId, int months) {
        LocalDate startDate = LocalDate.now().minusMonths(months);
        List<TransactionOperation> transactions = transactionRepository.findRecentTransactions(userId, startDate);
        
        return transactions.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<TransactionDto> getTransactionsByPeriod(String userId, LocalDate startDate, LocalDate endDate) {
        List<TransactionOperation> transactions = transactionRepository.findByUserIdAndDateBetween(userId, startDate, endDate);
        
        return transactions.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private TransactionDto mapToDto(TransactionOperation transaction) {
        return TransactionDto.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .category(transaction.getCategory())
                .date(transaction.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .description(transaction.getDescription())
                .type(transaction.getType())
                .build();
    }
}
