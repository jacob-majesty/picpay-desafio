package com.majesty.picpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.majesty.picpay.domain.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByDocument(String document);

    Optional<User> findUserById(Long Id);

}
