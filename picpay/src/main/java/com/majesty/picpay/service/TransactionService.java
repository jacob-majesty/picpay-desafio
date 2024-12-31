package com.majesty.picpay.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.majesty.picpay.domain.transaction.Transaction;
import com.majesty.picpay.domain.user.User;
import com.majesty.picpay.dto.TransactionDTO;
import com.majesty.picpay.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    public void createTransaction(TransactionDTO transaction) throws Exception {
        User sender = userService.findUserById(transaction.senderId());
        User receiver = userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.value());

        boolean isAuthorized = authorizeTransaction(sender, transaction.value());
        if (!isAuthorized) {
            throw new Exception("Transação não autorizada");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        this.transactionRepository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

    }

    public boolean authorizeTransaction(User sender, BigDecimal amount) {

        // Simula autorização externa
        String authorizationUrl = "https://util.devi.tools/api/v2/authorize";

        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity(authorizationUrl, Map.class);

        if (authorizationResponse.getStatusCode() == HttpStatusCode.valueOf(Response.SC_OK)) {
            String message = authorizationResponse.getBody().get("message").toString();
            return "Autorizado".equalsIgnoreCase(message);
        } else {
            return false;
        }
    }

}
