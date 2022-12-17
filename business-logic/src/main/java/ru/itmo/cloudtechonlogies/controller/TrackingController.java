package ru.itmo.cloudtechonlogies.controller;

import com.github.dockerjava.api.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.cloudtechonlogies.dto.TrackingDTOResponse;
import ru.itmo.cloudtechonlogies.exception.NotFoundElementException;
import ru.itmo.cloudtechonlogies.mapper.TrackingMapper;
import ru.itmo.cloudtechonlogies.model.Tracking;
import ru.itmo.cloudtechonlogies.model.User;
import ru.itmo.cloudtechonlogies.service.BookService;
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

    private final BookService bookService;

    @GetMapping("{bookId}")
    public ResponseEntity<TrackingDTOResponse> getTracking(Principal principal, @PathVariable UUID bookId) {
        User user = userService.findByLogin(principal.getName());
        TrackingDTOResponse dtoResponse = trackingMapper
                .mapEntityToDTO(trackingService.getTrackingByUserAndBook(user, bookService.getById(bookId).orElseThrow(() -> new NotFoundElementException("This book doesn't exist"))));
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

}
