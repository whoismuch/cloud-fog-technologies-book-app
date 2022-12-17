package ru.itmo.cloudtechonlogies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.highloadsystems.model.DeliveryAct;

import java.util.List;
import java.util.UUID;

public interface DeliveryActRepository extends JpaRepository<DeliveryAct, UUID> {

    List<DeliveryAct> findByOrderId(UUID orderId);

    List<DeliveryAct> findByUserId(UUID userId);
}
