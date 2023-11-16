package com.github.rodis00.astrosales.repository;

import com.github.rodis00.astrosales.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByUserId(Integer id);
    @Query("SELECT t.id FROM Transaction t WHERE t.flight.id = :id")
    List<Integer> findIdByFlightId(@Param("id") Integer id);
}
