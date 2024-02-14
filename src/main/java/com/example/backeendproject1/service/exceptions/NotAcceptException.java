package com.example.backeendproject1.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class NotAcceptException extends RuntimeException {
    public NotAcceptException(String message) {
        super(message);
    }
}
