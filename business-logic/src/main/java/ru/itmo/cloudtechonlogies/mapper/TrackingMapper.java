package ru.itmo.cloudtechonlogies.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itmo.cloudtechonlogies.dto.TrackingDTOResponse;
import ru.itmo.cloudtechonlogies.model.Tracking;

@RequiredArgsConstructor
@Component
public class TrackingMapper {


    public TrackingDTOResponse mapEntityToDTO(Tracking tracking) {
        TrackingDTOResponse dtoResponse = TrackingDTOResponse.builder()
                .page(tracking.getPage())
                .timer(tracking.getTimer())
                .build();
        return dtoResponse;
    }
}
