package ru.itmo.cloudtechonlogies.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.itmo.highloadsystems.dto.DeliveryActDTOResponse;
import ru.itmo.highloadsystems.dto.OrdersUsersDTOResponse;
import ru.itmo.highloadsystems.model.DeliveryAct;

@RequiredArgsConstructor
@Component
public class DeliveryActMapper {

    private final ModelMapper modelMapper;

    public DeliveryActDTOResponse mapEntityToDto(DeliveryAct deliveryAct) {
        DeliveryActDTOResponse deliveryActDTOResponse = modelMapper.map(deliveryAct, DeliveryActDTOResponse.class);
        deliveryActDTOResponse.setOrder(modelMapper.map(deliveryAct.getOrder(), OrdersUsersDTOResponse.class));
        return deliveryActDTOResponse;
    }
}
