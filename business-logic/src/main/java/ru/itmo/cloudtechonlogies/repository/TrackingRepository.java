package ru.itmo.cloudtechonlogies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.cloudtechonlogies.model.Book;
import ru.itmo.cloudtechonlogies.model.Role;
import ru.itmo.cloudtechonlogies.model.Tracking;
import ru.itmo.cloudtechonlogies.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TrackingRepository extends JpaRepository<Tracking, UUID> {

    Optional<Tracking> getTrackingByUserAndBook(User user, Book book);

    List<Tracking> getTrackingsByUser(User user);
}
