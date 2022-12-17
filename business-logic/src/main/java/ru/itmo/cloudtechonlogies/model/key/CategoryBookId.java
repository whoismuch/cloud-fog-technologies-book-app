package ru.itmo.cloudtechonlogies.model.key;

import ru.itmo.cloudtechonlogies.model.Book;
import ru.itmo.cloudtechonlogies.model.Category;

import java.io.Serializable;
import java.util.UUID;

public class CategoryBookId implements Serializable {

    private Category category;
    private Book book;

    public CategoryBookId(Category category, Book book) {
        this.category = category;
        this.book = book;
    }

}
