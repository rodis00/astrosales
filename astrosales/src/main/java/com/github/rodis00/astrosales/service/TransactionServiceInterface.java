package com.github.rodis00.astrosales.service;

import com.github.rodis00.astrosales.model.Transaction;

public interface TransactionServiceInterface {
    Transaction saveTransaction(Transaction transaction);
    Transaction getTransactionById(Integer id);
    Transaction getTransactionByUserId(Integer id);
    void deleteTransaction(Integer id);
}
