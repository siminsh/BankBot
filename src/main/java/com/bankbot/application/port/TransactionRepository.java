package com.bankbot.application.port;

import com.bankbot.domain.model.TransactionOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionOperation, Long> {
    
    List<TransactionOperation> findByUserId(String userId);
    
    List<TransactionOperation> findByUserIdAndDateBetween(String userId, LocalDate startDate, LocalDate endDate);
    
    @Query("SELECT t FROM TransactionOperation t WHERE t.userId = :userId AND t.date >= :startDate ORDER BY t.date DESC")
    List<TransactionOperation> findRecentTransactions(@Param("userId") String userId, @Param("startDate") LocalDate startDate);
    
    @Query("SELECT t.category, SUM(t.amount) FROM TransactionOperation t WHERE t.userId = :userId AND t.date BETWEEN :startDate AND :endDate GROUP BY t.category ORDER BY SUM(t.amount) DESC")
    List<Object[]> findSpendingByCategory(@Param("userId") String userId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
