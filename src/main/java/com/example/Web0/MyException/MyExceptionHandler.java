package com.example.Web0.MyException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Object> handler(RuntimeException ex){
        return ResponseEntity.status(400).body(ex.getMessage());
    }
}
