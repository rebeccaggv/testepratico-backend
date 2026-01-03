package com.farmtech.testepratico_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // captura erro dos controllers
public class GlobalExceptionHandler {

    // metodo p capturar erro de nomes duplicados
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleBusinessException(IllegalArgumentException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Regra de Negócio");
        error.put("message", ex.getMessage()); // pega a mensagem do service
        // retorna HTTP 409 p indicar o conflito, duplicidade de dados
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    // metodo p capturar erros de validação, em branco, tamanho superior etc
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        // itera sobre todos os campos que falharam na validação p retornar uma lista limpa
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        // retorna HTTP 400 pq o usuario enviou dados inválidos
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}