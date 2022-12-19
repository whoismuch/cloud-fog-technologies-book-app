package ru.itmo.cloudtechonlogies.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.cloudtechonlogies.exception.NotFoundElementException;
import ru.itmo.cloudtechonlogies.model.Book;
import ru.itmo.cloudtechonlogies.model.Tracking;
import ru.itmo.cloudtechonlogies.model.User;
import ru.itmo.cloudtechonlogies.repository.TrackingRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrackingService {

    private final TrackingRepository trackingRepository;
    public List<Tracking> getAllByUser(User user) {
        return trackingRepository.getTrackingsByUser(user);
    }
    public Optional<Tracking> getTrackingByUserAndBook(User user, Book book) {
        return trackingRepository.getTrackingByUserAndBook(user, book);
    }

    public Tracking createTracking(User user, Book book) {
        Tracking tracking = Tracking.builder()
                .user(user)
                .book(book)
                .page(1)
                .timer(1)
                .build();

        return trackingRepository.save(tracking);
    }

    public Tracking updateTracking(Tracking tracking, User user, Book book) {
        Optional<Tracking> oldTracking = getTrackingByUserAndBook(user, book);
        if (oldTracking.isEmpty()) throw new NotFoundElementException("There is no tracking for this user and book");
        if (tracking.getPage() != null) {
            oldTracking.get().setPage(tracking.getPage());
            oldTracking.get().setTimer(book.getAudioLength() * tracking.getPage()/book.getPageCount());
        }
        else if (tracking.getTimer() != null) {
            oldTracking.get().setTimer(tracking.getTimer());
            oldTracking.get().setPage(book.getPageCount() * tracking.getTimer()/book.getAudioLength());
        }
        return trackingRepository.save(oldTracking.get());
    }

}
