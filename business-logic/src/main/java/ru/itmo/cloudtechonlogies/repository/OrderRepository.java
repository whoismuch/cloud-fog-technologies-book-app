package ru.itmo.cloudtechonlogies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itmo.highloadsystems.model.Order;
import ru.itmo.highloadsystems.model.User;

import java.util.List;
import java.util.UUID;

public interface OrderRepository  extends JpaRepository<Order, UUID> {

    List<Order> findAllByUser(User user);

    @Query(value = "select public.order.id, public.order.user_id, public.order.date, public.order.location from public.order left join  delivery_act on delivery_act.order_id = public.order.id where delivery_act.id is null or delivery_act.status = 'DENIED'",  nativeQuery = true)
    List<Order> findAllFreeOrder();
}
