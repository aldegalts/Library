package com.degaltseva.library.service;

import com.degaltseva.library.entity.BookEntity;

import java.util.List;

/**
 * Сервис для работы с книгами.
 * <p>
 * Возможности: добавление, получение, обновление, удаление и поиск книг по названию и году публикации.
 */
public interface BookService {

    List<BookEntity> getAllBooks();

    BookEntity getBookById(Long id);

    BookEntity createBook(BookEntity book);

    BookEntity updateBook(Long id, BookEntity book);

    void deleteBook(Long id);

    List<BookEntity> findByTitleAndYearRange(String title, int startYear, int endYear);
}
