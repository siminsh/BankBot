package com.bankbot.infrastructure.repository;

import com.bankbot.application.port.TransactionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepositoryImpl extends TransactionRepository {
    // Spring Data JPA will automatically implement the methods defined in TransactionRepository
}
