package ru.itmo.cloudtechonlogies.model.key;

import ru.itmo.cloudtechonlogies.model.Book;
import ru.itmo.cloudtechonlogies.model.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public class TrackingId implements Serializable {

    private Book book;
    private User user;

    public TrackingId(Book book, User user) {
        this.book = book;
        this.user = user;
    }
}
