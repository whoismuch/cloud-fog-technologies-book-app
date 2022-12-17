package ru.itmo.cloudtechonlogies.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class OrdersUsersDTOResponse {
    private UUID id;

    @JsonProperty("user")
    private UserDTOResponse user;

    private LocalDateTime date;

    private String location;
}
