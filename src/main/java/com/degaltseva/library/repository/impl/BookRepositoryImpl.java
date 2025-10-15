package com.degaltseva.library.repository.impl;

import com.degaltseva.library.entity.Book;
import com.degaltseva.library.repository.BookCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Реализация репозитория для книг в "базе данных".
 * <p>
 * Возможности: создание, чтение, обновление и удаление книг.
 */
@Component
public class BookRepositoryImpl implements BookCrudRepository {

    private final List<Book> bookContainer;

    @Autowired
    public BookRepositoryImpl(List<Book> bookContainer) {
        this.bookContainer = bookContainer;
    }

    @Override
    public void create(Book entity) {
        bookContainer.add(entity);
    }

    @Override
    public Book read(Long id) {
        Optional<Book> book = bookContainer.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst();
        return book.orElse(null);
    }

    @Override
    public void update(Book book) {
        for (int i = 0; i < bookContainer.size(); i++) {
            if (bookContainer.get(i).getId().equals(book.getId())) {
                bookContainer.set(i, book);
                return;
            }
        }
    }

    @Override
    public void delete(Long id) {
        bookContainer.removeIf(b -> b.getId().equals(id));
    }
}
