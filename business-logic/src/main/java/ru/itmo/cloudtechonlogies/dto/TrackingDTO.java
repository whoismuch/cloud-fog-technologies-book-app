package ru.itmo.cloudtechonlogies.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public class TrackingDTO {
    private UUID userId;
    private UUID bookId;
    private Integer page;
    private Integer timer;

}
