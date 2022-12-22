package ru.itmo.cloudtechonlogies.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.cloudtechonlogies.exception.NoMatchException;
import ru.itmo.cloudtechonlogies.exception.NotFoundElementException;
import ru.itmo.cloudtechonlogies.model.Book;
import ru.itmo.cloudtechonlogies.model.Category;
import ru.itmo.cloudtechonlogies.model.CategoryBook;
import ru.itmo.cloudtechonlogies.repository.BookRepository;
import ru.itmo.cloudtechonlogies.repository.CategoryBookRepository;

@Service
@RequiredArgsConstructor
public class CategoryBookService {

    private final CategoryBookRepository categoryBookRepository;

    public CategoryBook getByBook(Book book) {
        return categoryBookRepository.getCategoryBookByBook(book).orElse(null);
    }
}
