package com.example.demo.repository;

import com.example.demo.entity.SimCardTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimCardTransactionRepository extends JpaRepository<SimCardTransaction, Long> {
    // no extra methods needed for this task
}

