package ru.itmo.cloudtechonlogies.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.cloudtechonlogies.model.Tracking;
import ru.itmo.cloudtechonlogies.model.User;
import ru.itmo.cloudtechonlogies.repository.TrackingRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrackingService {

    private final TrackingRepository trackingRepository;

    public List<Tracking> getAllByUser(User user) {
        return trackingRepository.getTrackingsByUser(user);
    }
}
