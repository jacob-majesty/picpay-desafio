package com.majesty.picpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.majesty.picpay.domain.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
