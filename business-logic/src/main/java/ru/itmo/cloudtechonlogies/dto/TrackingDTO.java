package ru.itmo.cloudtechonlogies.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class TrackingDTO {
    public UUID userId;
    public UUID bookId;
    public Integer page;
    public Integer timer;

    public TrackingDTO(UUID userId, UUID bookId, Integer page, Integer timer) {
        this.userId = userId;
        this.bookId = bookId;
        this.page = page;
        this.timer = timer;
    }

    public TrackingDTO() {
    }
}
