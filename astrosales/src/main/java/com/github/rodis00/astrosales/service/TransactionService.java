package com.github.rodis00.astrosales.service;

import com.github.rodis00.astrosales.exception.TransactionNotFoundException;
import com.github.rodis00.astrosales.model.Transaction;
import com.github.rodis00.astrosales.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService implements TransactionServiceInterface {
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction getTransactionById(Integer id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found."));
    }

    @Override
    public List<Transaction> getTransactionByUserId(Integer id) {
        return transactionRepository.findByUserId(id);
    }

    @Override
    public List<Transaction> getTransactionByUserIdGreaterOrEqualToday(Integer id, LocalDateTime currentDate) {
        return transactionRepository.findByUserIdAndFlight_DateOfFlightGreaterThanEqual(id, currentDate);
    }

    @Override
    public List<Integer> getTransactionsByFlightId(Integer id) {
        return transactionRepository.findIdByFlightId(id);
    }

    @Override
    public List<Transaction> saveAllTransactions(List<Transaction> transactions) {
        return transactionRepository.saveAll(transactions);
    }

    @Override
    public void deleteTransaction(Integer id) {
        Transaction transaction = getTransactionById(id);
        if (transaction != null)
            transactionRepository.delete(transaction);
    }
}
