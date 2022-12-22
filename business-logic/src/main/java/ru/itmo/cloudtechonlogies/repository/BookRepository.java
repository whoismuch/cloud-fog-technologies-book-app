package ru.itmo.cloudtechonlogies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itmo.cloudtechonlogies.model.Book;
import ru.itmo.cloudtechonlogies.model.Role;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    @Query(value = "select * from book inner join category_book on category_book.id_book = book.id inner join category on category_book.id_category = category.id where category.name = :category and book.id != :hasAlreadyBook LIMIT 1", nativeQuery = true)
    Optional<Book> findBookByCategory(String category, UUID hasAlreadyBook);
}
