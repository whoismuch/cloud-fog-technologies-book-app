package ru.itmo.cloudtechonlogies.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.itmo.cloudtechonlogies.model.Book;
import ru.itmo.cloudtechonlogies.model.CategoryBook;
import ru.itmo.cloudtechonlogies.model.Tracking;
import ru.itmo.cloudtechonlogies.model.User;
import ru.itmo.cloudtechonlogies.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final CategoryBookService categoryBookService;
    private final TrackingService trackingService;

    public Page<Book> findAll(int page, int size){
        return bookRepository.findAll(PageRequest.of(page, size));
    }

    public Page<CategoryBook> getAllWithCategory(int page, int size){
        List<Book> books = findAll(page, size).getContent();
        List<CategoryBook> categoryBooks = books.stream().map(categoryBookService::getByBook).toList();
        return new PageImpl<>(categoryBooks, PageRequest.of(page, size), categoryBooks.size());
    }

    public List<CategoryBook> getAllWithCategoryByUser(User user){
        List<Book> books = trackingService.getAllByUser(user).stream().map(Tracking::getBook).toList();
        List<CategoryBook> categoryBooks = books.stream().map(categoryBookService::getByBook).toList();
        return categoryBooks;
    }

}
