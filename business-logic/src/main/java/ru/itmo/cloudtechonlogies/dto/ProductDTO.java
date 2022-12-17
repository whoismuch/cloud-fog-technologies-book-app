package ru.itmo.cloudtechonlogies.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    UUID antistressToyTypeId;
    int count;
}
