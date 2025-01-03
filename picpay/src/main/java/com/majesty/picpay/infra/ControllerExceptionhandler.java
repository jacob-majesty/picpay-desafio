package com.majesty.picpay.infra;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.majesty.picpay.dto.ExceptionDTO;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ControllerExceptionhandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity threatDuplicateEntity(DataIntegrityViolationException ex) {

        ExceptionDTO exceptionDTO = new ExceptionDTO(ex.getMessage(), "Usuario ja cadastrado!");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threatEntityNotFound(EntityNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity threatException(Exception ex) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(ex.getMessage(), "Erro interno do servidor!");
        return ResponseEntity.internalServerError().body(exceptionDTO);
    }
}