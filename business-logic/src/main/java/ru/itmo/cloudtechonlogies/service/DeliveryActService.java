package ru.itmo.cloudtechonlogies.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.highloadsystems.enums.DeliveryStatus;
import ru.itmo.highloadsystems.exception.NotFoundElementException;
import ru.itmo.highloadsystems.model.DeliveryAct;
import ru.itmo.highloadsystems.model.Order;
import ru.itmo.highloadsystems.model.User;
import ru.itmo.highloadsystems.repository.DeliveryActRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryActService {

    private final DeliveryActRepository deliveryActRepository;

    private final OrderService orderService;

    public void saveDeliveryAct(DeliveryAct deliveryAct) {
        deliveryActRepository.save(deliveryAct);
    }

    public void assigmentDeliveryManOnOrder(UUID orderId, User user) throws NotFoundElementException {
        Order order = orderService.findById(orderId).orElseThrow(() -> new NotFoundElementException("Not found order"));
        DeliveryAct deliveryAct = new DeliveryAct();
        deliveryAct.setOrder(order);
        deliveryAct.setStatus(DeliveryStatus.IN_PROCESS);
        deliveryAct.setUser(user);
        this.saveDeliveryAct(deliveryAct);
    }

    public void updateStatus(UUID deliveryActId, String status) throws NotFoundElementException {
        DeliveryAct deliveryAct = deliveryActRepository.findById(deliveryActId)
                .orElseThrow(() -> new NotFoundElementException("Not found deliveryAct"));
        deliveryAct.setStatus(DeliveryStatus.valueOf(status));
        deliveryActRepository.save(deliveryAct);
    }

    public List<DeliveryAct> findDeliveryActByDeliverymanId(User deliveryMan) {
        return deliveryActRepository.findByUserId(deliveryMan.getId());
    }
}
