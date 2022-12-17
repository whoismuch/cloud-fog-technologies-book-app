package ru.itmo.cloudtechonlogies.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class OrderDTOResponse {

    UUID id;
    LocalDateTime date;
    String location;
    List<ProductDTO> productList;

}
