package ru.itmo.cloudtechonlogies.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageTimerDTO {
    private Integer page;
    private Integer timer;
}
