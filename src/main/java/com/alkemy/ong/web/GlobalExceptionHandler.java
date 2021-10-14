package com.alkemy.ong.web;


import com.alkemy.ong.domain.exceptions.DomainException;
import com.alkemy.ong.domain.members.MemberDomainException;
import com.alkemy.ong.domain.testimonials.TestimonialDomainException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
            WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        // Get all errors
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(x -> x.getDefaultMessage()).collect(Collectors.toList());

        body.put("timestamp", new Date());
        body.put("status", status.value());
        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);
    }

    // handling specific exception
    @ExceptionHandler(DomainException.class)
    public ResponseEntity<?> resourceNotFoundHandling(DomainException exception, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("message", exception.getMessage());
        body.put("details", request.getDescription(false));
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {MemberDomainException.class})
    public ResponseEntity<?> handleMemberException(Exception e) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> resourceNotFoundHandling(AccessDeniedException exception, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("message", exception.getMessage());
        body.put("details", request.getDescription(false));
        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(value = {TestimonialDomainException.class})
    protected ResponseEntity <?> hangTestimonialException(Exception e){
        return ResponseEntity.notFound().build();
    }
}

