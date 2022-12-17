package ru.itmo.cloudtechonlogies.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.highloadsystems.dto.ErrorDTO;
import ru.itmo.highloadsystems.dto.OrderDTORequest;
import ru.itmo.highloadsystems.dto.OrderDTOResponse;
import ru.itmo.highloadsystems.mapper.OrderMapper;
import ru.itmo.highloadsystems.model.Order;
import ru.itmo.highloadsystems.model.User;
import ru.itmo.highloadsystems.service.OrderService;
import ru.itmo.highloadsystems.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/order")
public class OrderController {

    private final UserService userService;

    private final OrderService orderService;

    private final OrderMapper orderMapper;

    @PostMapping("/")
//    @Secured({"ROLE_USER", "ROLE_DELIVERY"})
    public ResponseEntity<?> addOrder(Principal principal, @RequestBody @Valid OrderDTORequest orderDTORequest) {
        User user = userService.findByLogin(principal.getName());
        try {
            Order order = orderMapper.mapDtoToEntity(orderDTORequest);
            order.setUser(user);
            Order savedOrder = orderService.addOrder(order);
            OrderDTOResponse orderDTOResponse = orderMapper.mapEntityToDto(savedOrder);
            return new ResponseEntity<>(orderDTOResponse, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Not found order", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> getOrdersByUser(Principal principal, @PathVariable("id") UUID id) {
        User user = userService.findByLogin(principal.getName());
        if (user == null)
            return new ResponseEntity<>(new ErrorDTO("User not found"), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(orderService.getOrdersByUserId(id), HttpStatus.OK);
    }


}
