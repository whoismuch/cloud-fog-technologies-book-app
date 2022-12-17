package ru.itmo.cloudtechonlogies.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.itmo.highloadsystems.dto.ErrorDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice("MyExceptionHandler")
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundElementException.class)
    public ResponseEntity<?> exceptionNotFound(Exception e) {
        return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExistElementException.class)
    public ResponseEntity<?> exceptionAlreadyExist(Exception e) {
        return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoMatchException.class)
    public ResponseEntity<?> noMatchException(Exception e) {
        System.out.println("ooooops");
        return new ResponseEntity<>(new ErrorDTO("бла бла бла"), HttpStatus.UNAUTHORIZED);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        System.out.println("FUCK");
        List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return handleExceptionInternal(ex, errorList, headers, HttpStatus.UNAUTHORIZED, request);
    }
}
