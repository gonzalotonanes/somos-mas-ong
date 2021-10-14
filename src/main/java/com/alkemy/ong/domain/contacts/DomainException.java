package com.alkemy.ong.domain.contacts;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DomainException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public DomainException(String message) {
        super(message);
    }
}