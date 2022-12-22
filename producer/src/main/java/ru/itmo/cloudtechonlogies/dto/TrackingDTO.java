package ru.itmo.cloudtechonlogies.dto;

import lombok.Builder;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Builder
public class TrackingDTO  implements Serializable {
    public UUID userId;
    public UUID bookId;
    public Integer page;
    public Integer timer;

}
