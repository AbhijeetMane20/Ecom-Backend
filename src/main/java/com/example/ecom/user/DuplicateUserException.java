package com.example.ecom.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateUserException extends Exception {
    DuplicateUserException(String message) {
        super(message);
    }
}
