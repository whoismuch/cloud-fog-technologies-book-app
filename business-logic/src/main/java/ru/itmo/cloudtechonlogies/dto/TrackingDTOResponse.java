package ru.itmo.cloudtechonlogies.dto;

import lombok.Builder;
import org.springframework.data.relational.core.sql.In;

import java.util.UUID;

@Builder
public class TrackingDTOResponse {
    private UUID userId;
    private UUID bookId;
    private Integer page;
    private Integer timer;

}
