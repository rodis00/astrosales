package com.github.rodis00.astrosales.transaction;

import com.github.rodis00.astrosales.transaction.exception.TransactionNotFoundException;
import com.github.rodis00.astrosales.transaction.entity.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction getTransactionById(Integer id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found."));
    }

    public List<Transaction> getTransactionByUserId(Integer id) {
        return transactionRepository.findByUserId(id);
    }

    public List<Transaction> getTransactionByUserIdGreaterOrEqualToday(Integer id, LocalDateTime currentDate) {
        return transactionRepository.findByUserIdAndFlight_DateOfFlightGreaterThanEqual(id, currentDate);
    }

    public List<Integer> getTransactionsByFlightId(Integer id) {
        return transactionRepository.findIdByFlightId(id);
    }

    public List<Transaction> saveAllTransactions(List<Transaction> transactionEntities) {
        return transactionRepository.saveAll(transactionEntities);
    }

    public void deleteTransaction(Integer id) {
        Transaction transaction = getTransactionById(id);
        if (transaction != null)
            transactionRepository.delete(transaction);
    }
}
