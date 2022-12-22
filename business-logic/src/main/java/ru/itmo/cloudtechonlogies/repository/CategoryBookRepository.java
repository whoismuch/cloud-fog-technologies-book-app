package ru.itmo.cloudtechonlogies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itmo.cloudtechonlogies.model.Book;
import ru.itmo.cloudtechonlogies.model.CategoryBook;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryBookRepository  extends JpaRepository<CategoryBook, UUID> {

    Optional<CategoryBook> getCategoryBookByBook(Book book);
}
