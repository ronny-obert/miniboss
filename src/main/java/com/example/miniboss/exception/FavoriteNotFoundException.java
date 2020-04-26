package com.example.miniboss.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class FavoriteNotFoundException extends RuntimeException {
    public FavoriteNotFoundException(String s) {
        super(s);

    }
}
