package ru.itmo.cloudtechonlogies.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.cloudtechonlogies.dto.CategoryBookDTO;
import ru.itmo.cloudtechonlogies.exception.NotFoundElementException;
import ru.itmo.cloudtechonlogies.mapper.CategoryBookMapper;
import ru.itmo.cloudtechonlogies.model.Book;

import ru.itmo.cloudtechonlogies.model.CategoryBook;
import ru.itmo.cloudtechonlogies.model.User;
import ru.itmo.cloudtechonlogies.service.BookService;
import ru.itmo.cloudtechonlogies.service.ObjectStorageService;
import ru.itmo.cloudtechonlogies.service.UserService;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.nio.file.NoSuchFileException;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/book")
public class BookController {

    private final BookService bookService;
    private final UserService userService;
    private final CategoryBookMapper categoryBookMapper;
    private final ObjectStorageService objectStorageService;

    @GetMapping("")
    public ResponseEntity<?> getAllBooks(
            @RequestParam(defaultValue = "0") @Min(value = 0, message = "should be more 0") int page,
            @RequestParam(defaultValue = "5") @Max(value = 50, message = "should be less 50") int size) {

        Page<CategoryBookDTO> dtoPage = categoryBookMapper.mapEntityToDtoPage(bookService.getAllWithCategory(page, size));
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("totalElements", String.valueOf(dtoPage.getTotalElements()));
        responseHeaders.set("totalPage", String.valueOf(dtoPage.getTotalPages()));
        return new ResponseEntity<>(dtoPage.getContent(), responseHeaders, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getBooksByUser(Principal principal) {
        User user = userService.findByLogin(principal.getName());
        List<CategoryBookDTO> dtoList = bookService.getAllWithCategoryByUser(user).stream().map(categoryBookMapper::mapEntityToDto).toList();
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/{bookId}/audio")
    public ResponseEntity<?> getAudioBook(Principal principal, @PathVariable UUID bookId) {
        bookService.getById(bookId)
                .orElseThrow(() -> new NotFoundElementException("This book doesn't exist"));

        try {
            final byte[] data = objectStorageService.getFile("bookstorage", "audio/" + bookId + ".mp3");
            final ByteArrayResource resource = new ByteArrayResource(data);
            return ResponseEntity
                    .ok()
                    .contentLength(data.length)
                    .header("Content-type", "application/octet-stream")
                    .header("Content-disposition", "attachment; filename=\"" + bookId + ".mp3\"")
                    .header("Cache-Control", "no-cache")
                    .body(resource);
        } catch (Exception e) {
            throw new NotFoundElementException("Not found file of book in storage");
        }
    }

    @GetMapping("/{bookId}/text")
    public ResponseEntity<?> getTextBook(Principal principal, @PathVariable UUID bookId) {
        bookService.getById(bookId)
                .orElseThrow(() -> new NotFoundElementException("This book doesn't exist"));

        try {
            final byte[] data = objectStorageService.getFile("bookstorage", "text/" + bookId + ".epub");
            final ByteArrayResource resource = new ByteArrayResource(data);
            return ResponseEntity
                    .ok()
                    .contentLength(data.length)
                    .header("Content-type", "application/octet-stream")
                    .header("Content-disposition", "attachment; filename=\"" + bookId + ".epub\"")
                    .header("Cache-Control", "no-cache")
                    .body(resource);
        } catch (Exception e) {
            throw new NotFoundElementException("Not found file of book in storage");
        }
    }
}
