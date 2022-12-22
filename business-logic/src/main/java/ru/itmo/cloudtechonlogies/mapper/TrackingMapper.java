package ru.itmo.cloudtechonlogies.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itmo.cloudtechonlogies.dto.PageTimerDTO;
import ru.itmo.cloudtechonlogies.dto.TrackingDTO;
import ru.itmo.cloudtechonlogies.model.Tracking;

@RequiredArgsConstructor
@Component
public class TrackingMapper {


    public PageTimerDTO mapEntityToPageTimerDTO(Tracking tracking) {
        PageTimerDTO dtoResponse = PageTimerDTO.builder()
                .page(tracking.getPage())
                .timer(tracking.getTimer())
                .build();
        return dtoResponse;
    }

    public TrackingDTO mapEntityToDTO(Tracking tracking) {
        return TrackingDTO.builder()
                .userId(tracking.getUser().getId())
                .bookId(tracking.getBook().getId())
                .page(tracking.getPage())
                .timer(tracking.getTimer())
                .build();
    }

    public Tracking mapPageTimerDTOToEntity(PageTimerDTO pageTimerDTO) {
        Tracking tracking = Tracking.builder()
                .page(pageTimerDTO.getPage())
                .timer(pageTimerDTO.getTimer())
                .build();
        return tracking;
    }


}
