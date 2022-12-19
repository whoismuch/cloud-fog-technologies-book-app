package ru.itmo.cloudtechonlogies.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public class TrackingDTOResponse {
    private UUID userId;
    private UUID bookId;
    private Integer page;
    private Long timer;

}
