package com.ecore.roles.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectsNullException extends RuntimeException {

    public <T> RequiredObjectsNullException() {
        super("It`s not allowed to persist a null object!");
    }
}
