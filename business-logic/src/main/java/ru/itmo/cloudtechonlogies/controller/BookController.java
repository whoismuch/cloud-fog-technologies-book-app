package ru.itmo.cloudtechonlogies.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.cloudtechonlogies.model.Book;

import ru.itmo.cloudtechonlogies.service.BookService;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/book")
public class BookController {

    private final BookService bookService;

    @GetMapping("")
    public ResponseEntity<?> getAllBooks(
            @RequestParam(defaultValue = "0") @Min(value = 0, message = "should be more 0") int page,
            @RequestParam(defaultValue = "5") @Max(value = 50, message = "should be less 50") int size
    ) {
        try {

            Page<Book> pageBook = bookService.findAll(page, size);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("totalElements", String.valueOf(pageBook.getTotalElements()));
            responseHeaders.set("totalPage", String.valueOf(pageBook.getTotalPages()));
            return new ResponseEntity<>(pageBook.getContent(), responseHeaders, HttpStatus.OK);
        }catch (Throwable e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }
}
