package ru.itmo.cloudtechonlogies.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.itmo.highloadsystems.dto.OrderDTORequest;
import ru.itmo.highloadsystems.dto.OrderDTOResponse;
import ru.itmo.highloadsystems.dto.OrdersUsersDTOResponse;
import ru.itmo.highloadsystems.dto.UserDTOResponse;
import ru.itmo.highloadsystems.model.Order;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class OrderMapper {

    private final ModelMapper modelMapper;
    private final ProductMapper productMapper;

    public Order mapDtoToEntity(OrderDTORequest orderDTORequest) {
        Order order = modelMapper.map(orderDTORequest, Order.class);
        order.setDate(LocalDateTime.now());
        order.setProductList(orderDTORequest.getProductList().stream().map(productMapper::mapDtoToEntity).collect(Collectors.toList()));
        return order;
    }

    public OrderDTOResponse mapEntityToDto(Order order) {
        OrderDTOResponse orderDTOResponse = modelMapper.map(order, OrderDTOResponse.class);
        orderDTOResponse.setProductList(order.getProductList().stream().map(productMapper::mapEntityToDto).collect(Collectors.toList()));
        return orderDTOResponse;
    }

    public OrdersUsersDTOResponse mapEntityToDtoUser(Order order) {
        OrdersUsersDTOResponse ordersUsersDTOResponse = modelMapper.map(order, OrdersUsersDTOResponse.class);
        ordersUsersDTOResponse.setUser(modelMapper.map(order.getUser(), UserDTOResponse.class));
        return ordersUsersDTOResponse;
    }
}

