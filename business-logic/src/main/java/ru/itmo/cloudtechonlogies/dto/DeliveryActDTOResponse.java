package ru.itmo.cloudtechonlogies.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class DeliveryActDTOResponse {

    private UUID uuid;

    private String status;

    @JsonProperty("order")
    private OrdersUsersDTOResponse order;
}
