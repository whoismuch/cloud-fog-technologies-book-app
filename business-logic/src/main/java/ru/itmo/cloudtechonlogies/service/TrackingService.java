package ru.itmo.cloudtechonlogies.service;

import com.github.dockerjava.api.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.cloudtechonlogies.exception.NotFoundElementException;
import ru.itmo.cloudtechonlogies.model.Book;
import ru.itmo.cloudtechonlogies.model.Tracking;
import ru.itmo.cloudtechonlogies.model.User;
import ru.itmo.cloudtechonlogies.repository.TrackingRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrackingService {

    private final TrackingRepository trackingRepository;

    public List<Tracking> getAllByUser(User user) {
        return trackingRepository.getTrackingsByUser(user);
    }
    public Tracking getTrackingByUserAndBook(User user, Book book) {
        return trackingRepository.getTrackingByUserAndBook(user, book)
                .orElseThrow(() -> new NotFoundException("User isn't reading that book"));
    }

}
