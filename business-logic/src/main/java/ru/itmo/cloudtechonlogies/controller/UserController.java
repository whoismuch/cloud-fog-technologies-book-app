package ru.itmo.cloudtechonlogies.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.cloudtechonlogies.dto.AuthDTORequest;
import ru.itmo.cloudtechonlogies.dto.UserDTORequest;
import ru.itmo.cloudtechonlogies.filter.JwtProvider;
import ru.itmo.cloudtechonlogies.model.User;
import ru.itmo.cloudtechonlogies.service.UserService;
import ru.itmo.cloudtechonlogies.service.listener.TrackingMessageListenerService;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    private final TrackingMessageListenerService trackingMessageListenerService;

    private final JwtProvider jwtProvider;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserDTORequest userDTORequest) {
        userService.saveUser(userDTORequest);
        return new ResponseEntity<>("User saved", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> auth(@Valid @RequestBody AuthDTORequest request) {
        User userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(userEntity.getLogin());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @DeleteMapping("/{login}")
    public ResponseEntity<?> delete(@PathVariable String login) {
        User user = userService.findByLogin(login);
        userService.deleteUser(user);
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getListUser(
            @RequestParam(defaultValue = "0") @Min(value = 0, message = "should be more 0") int page,
            @RequestParam(defaultValue = "5") @Max(value = 50, message = "should be less 50") int size
    ) {
        try {
            Page<User> userPage = userService.findAll(page, size);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("totalElements", String.valueOf(userPage.getTotalElements()));
            responseHeaders.set("totalPage", String.valueOf(userPage.getTotalPages()));
            return new ResponseEntity<>(userPage.getContent(), responseHeaders, HttpStatus.OK);
        } catch (Throwable e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @GetMapping("/track")
    public ResponseEntity<?> track() {
        trackingMessageListenerService.initConsumer();
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}