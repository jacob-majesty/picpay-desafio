package com.majesty.picpay.dto;

import java.math.BigDecimal;

import com.majesty.picpay.domain.user.UserType;

public record UserDTO(String firstName, String lastName, String document, String email, String password,
        BigDecimal balance, UserType userType) {

}
