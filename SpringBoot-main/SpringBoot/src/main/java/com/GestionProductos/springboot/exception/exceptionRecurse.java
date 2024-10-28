package com.GestionProductos.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class exceptionRecurse extends RuntimeException {
    public exceptionRecurse(String message) {
        super(message);
    }
}