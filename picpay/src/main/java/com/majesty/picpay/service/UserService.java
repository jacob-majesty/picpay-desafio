package com.majesty.picpay.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.majesty.picpay.domain.user.User;
import com.majesty.picpay.domain.user.UserType;
import com.majesty.picpay.dto.UserDTO;
import com.majesty.picpay.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal amount) {
        if (sender.getUserType() == UserType.MERCHANT) {
            throw new RuntimeException("Usuário lojista não pode realizar transação");
        }

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }

    }

    public User findUserById(Long id) {

        return this.userRepository.findUserById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    public User createUser(UserDTO user) {
        User newUser = new User(data);
        this.saveUser(newUser);

}
