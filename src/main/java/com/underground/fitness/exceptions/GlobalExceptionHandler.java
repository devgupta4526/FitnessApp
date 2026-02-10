package com.underground.fitness.exceptions;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public ResponseEntity<Map<String,String>> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String,String> map = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach((error)->{
                    map.put(error.getField(),
                            error.getDefaultMessage());});
        return ResponseEntity.badRequest().body(map);
    }


}
