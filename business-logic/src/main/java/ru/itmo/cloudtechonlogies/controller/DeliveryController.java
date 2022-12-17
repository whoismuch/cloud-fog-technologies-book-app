package ru.itmo.cloudtechonlogies.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.highloadsystems.mapper.DeliveryActMapper;
import ru.itmo.highloadsystems.mapper.MapperUtil;
import ru.itmo.highloadsystems.mapper.OrderMapper;
import ru.itmo.highloadsystems.model.DeliveryAct;
import ru.itmo.highloadsystems.model.Order;
import ru.itmo.highloadsystems.model.User;
import ru.itmo.highloadsystems.service.DeliveryActService;
import ru.itmo.highloadsystems.service.OrderService;
import ru.itmo.highloadsystems.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryActService deliveryActService;

    private final OrderService orderService;

    private final UserService userService;

    private final OrderMapper orderMapper;

    private final DeliveryActMapper deliveryActMapper;

    @GetMapping("/list/orders/free")
    public ResponseEntity<?> listFreeDeliveries() {
        List<Order> orderList = orderService.findAllFreeOrder();
        return new ResponseEntity<>(MapperUtil.convertList(orderList, orderMapper::mapEntityToDto), HttpStatus.OK);
    }

    @PostMapping("/assignment")
    public ResponseEntity<?> deliveryAssignment(Principal principal, @RequestParam("order") UUID order) {
        User user = userService.findByLogin(principal.getName());
        deliveryActService.assigmentDeliveryManOnOrder(order, user);
        return new ResponseEntity<>("Deliveryman assigned", HttpStatus.OK);
    }

    @PutMapping("/status")
    public ResponseEntity<?> updateStatus(@RequestParam("deliveryAct") UUID deliveryAct, @RequestParam("status") String status) {
        deliveryActService.updateStatus(deliveryAct, status);
        return new ResponseEntity<>("Status updated", HttpStatus.OK);
    }

    @GetMapping("/list/acts")
    public ResponseEntity<?> listOrderByDeliveryman(Principal principal) {
        User user = userService.findByLogin(principal.getName());
        List<DeliveryAct> deliveryActList = deliveryActService.findDeliveryActByDeliverymanId(user);
        return new ResponseEntity<>(MapperUtil.convertList(deliveryActList, deliveryActMapper::mapEntityToDto), HttpStatus.OK);
    }
}
