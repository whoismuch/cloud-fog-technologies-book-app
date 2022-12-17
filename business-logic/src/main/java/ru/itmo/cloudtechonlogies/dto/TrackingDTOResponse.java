package ru.itmo.cloudtechonlogies.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrackingDTOResponse {

    private Integer page;
    private Long timer;
}
