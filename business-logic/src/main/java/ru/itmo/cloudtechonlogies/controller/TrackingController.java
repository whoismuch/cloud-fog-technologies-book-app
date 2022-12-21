package ru.itmo.cloudtechonlogies.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.cloudtechonlogies.dto.PageTimerDTO;
import ru.itmo.cloudtechonlogies.exception.NotFoundElementException;
import ru.itmo.cloudtechonlogies.mapper.TrackingMapper;
import ru.itmo.cloudtechonlogies.model.Tracking;
import ru.itmo.cloudtechonlogies.model.User;
import ru.itmo.cloudtechonlogies.service.BookService;
import ru.itmo.cloudtechonlogies.service.SmtpService;
import ru.itmo.cloudtechonlogies.service.TrackingService;
import ru.itmo.cloudtechonlogies.service.UserService;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/tracking")
public class TrackingController {

    private final TrackingService trackingService;
    private final TrackingMapper trackingMapper;
    private final UserService userService;
    private final SmtpService smtpService;

    private final BookService bookService;

    @GetMapping("{bookId}")
    public ResponseEntity<PageTimerDTO> getTracking(Principal principal, @PathVariable UUID bookId) {
        User user = userService.findByLogin(principal.getName());
        PageTimerDTO dtoResponse = trackingMapper
                .mapEntityToPageTimerDTO(trackingService.getTrackingByUserAndBook(user, bookService.getById(bookId)
                        .orElseThrow(() -> new NotFoundElementException("This book doesn't exist")))
                        .orElseThrow( () -> new NotFoundElementException("There is no tracking for this user and book")));
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @PostMapping("/{bookId}")
    public ResponseEntity<PageTimerDTO> createTracking(Principal principal, @PathVariable UUID bookId) {
        User user = userService.findByLogin(principal.getName());
        PageTimerDTO dtoResponse = trackingMapper
                .mapEntityToPageTimerDTO(trackingService.createTracking(user,bookService.getById(bookId)
                        .orElseThrow(() -> new NotFoundElementException("This book doesn't exist"))));

        smtpService.sendMessage()
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<PageTimerDTO> updateTracking(Principal principal, @RequestBody PageTimerDTO pageTimerDTO, @PathVariable UUID bookId) {
        User user = userService.findByLogin(principal.getName());
        Tracking tracking = trackingMapper.mapPageTimerDTOToEntity(pageTimerDTO);
        PageTimerDTO dtoResponse = trackingMapper
                .mapEntityToPageTimerDTO(trackingService.updateTracking(tracking, user, bookService.getById(bookId)
                        .orElseThrow(() -> new NotFoundElementException("This book doesn't exist"))));
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }


}
