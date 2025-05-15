package com.bankbot.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private Long id;
    private BigDecimal amount;
    private String category;
    private String date; // ISO format: YYYY-MM-DD
    private String description;
    private String type; // INCOME or EXPENSE
}
