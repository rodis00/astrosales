package com.github.rodis00.astrosales.service;

import com.github.rodis00.astrosales.model.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionServiceInterface {
    Transaction saveTransaction(Transaction transaction);
    Transaction getTransactionById(Integer id);
    List<Transaction> getTransactionByUserId(Integer id);
    List<Transaction> getTransactionByUserIdGreaterOrEqualToday(Integer id, LocalDateTime currentDate);
    List<Integer> getTransactionsByFlightId(Integer id);
    List<Transaction> saveAllTransactions(List<Transaction> transactions);
    void deleteTransaction(Integer id);
}
