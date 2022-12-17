package ru.itmo.cloudtechonlogies.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.highloadsystems.model.Order;
import ru.itmo.highloadsystems.model.User;
import ru.itmo.highloadsystems.repository.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final ProductService productService;

    private final UserService userService;

    @Transactional
    public Order addOrder(Order order) {
        order.setId(UUID.randomUUID());
        Order orderSaved = orderRepository.save(order);
        order.getProductList().forEach((m) -> productService.addProduct(m, orderSaved));
        return orderSaved;
    }

    public List<Order> getOrdersByUserId(UUID id) {
        User user = userService.findById(id);
        return orderRepository.findAllByUser(user);
    }

    public Optional<Order> findById(UUID orderId) {
        return orderRepository.findById(orderId);
    }

    public List<Order> findAllFreeOrder() {
        return orderRepository.findAllFreeOrder();
    }
}
