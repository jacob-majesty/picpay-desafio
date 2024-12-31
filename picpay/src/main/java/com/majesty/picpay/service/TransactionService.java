package com.majesty.picpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.majesty.picpay.dto.TransactionDTO;
import com.majesty.picpay.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    public void createTransaction(TransactionDTO transaction) {

    }

}
