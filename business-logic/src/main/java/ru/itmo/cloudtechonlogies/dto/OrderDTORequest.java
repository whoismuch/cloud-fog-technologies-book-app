package ru.itmo.cloudtechonlogies.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class OrderDTORequest {

    String location;
    List<ProductDTO> productList;

}
