package ru.itmo.cloudtechonlogies.controller;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.itmo.highloadsystems.exception.ExistElementException;
import ru.itmo.highloadsystems.exception.NoMatchException;
import ru.itmo.highloadsystems.exception.NotFoundElementException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionsHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundElementException.class)
    public ResponseEntity<?> exceptionNotFound(Exception e) {
        return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExistElementException.class)
    public ResponseEntity<?> exceptionAlreadyExist(Exception e) {
        return new ResponseEntity<Object>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoMatchException.class)
    public ResponseEntity<?> exceptionNoMuch(Exception e) {
        return new ResponseEntity<Object>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return handleExceptionInternal(ex, errorList, headers, HttpStatus.UNAUTHORIZED, request);
    }
}
