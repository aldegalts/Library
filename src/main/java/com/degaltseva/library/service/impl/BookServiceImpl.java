package com.degaltseva.library.service.impl;

import com.degaltseva.library.entity.BookEntity;
import com.degaltseva.library.repository.BookRepository;
import com.degaltseva.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Реализация сервиса для работы с книгами.
 */
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookEntity> getAllBooks() {
        List<BookEntity> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        return books;
    }

    @Override
    public BookEntity getBookById(Long id) {
        Optional<BookEntity> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    @Override
    public BookEntity createBook(BookEntity book) {
        return bookRepository.save(book);
    }

    @Override
    public BookEntity updateBook(Long id, BookEntity updatedBook) {
        Optional<BookEntity> existing = bookRepository.findById(id);
        if (existing.isPresent()) {
            BookEntity book = existing.get();
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setPublisher(updatedBook.getPublisher());
            book.setYearPublished(updatedBook.getYearPublished());
            book.setCategory(updatedBook.getCategory());
            book.setTotalCopies(updatedBook.getTotalCopies());
            book.setAvailableCopies(updatedBook.getAvailableCopies());
            return bookRepository.save(book);
        }
        return null;
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookEntity> findByTitleAndYearRange(String title, int startYear, int endYear) {
        return bookRepository.findByTitleAndYearPublishedBetween(title, startYear, endYear);
    }
}