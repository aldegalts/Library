package com.degaltseva.library.service.impl;

import com.degaltseva.library.entity.Book;
import com.degaltseva.library.repository.impl.BookRepository;
import com.degaltseva.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Реализация сервиса для работы с книгами.
 *
 * Возможности: создание, поиск, удаление и обновление книг.
 */
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void createBook(Long id, String title, String author, int year) {
        Book newBook = new Book();
        newBook.setId(id);
        newBook.setTitle(title);
        newBook.setAuthor(author);
        newBook.setYear(year);
        newBook.setAvailable(true);
        bookRepository.create(newBook);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.read(id);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.delete(id);
    }

    @Override
    public void updateBookData(Long id, String title, String author, int year, boolean isAvailable) {
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setAuthor(author);
        book.setYear(year);
        book.setAvailable(isAvailable);
        bookRepository.update(book);
    }
}
