package com.degaltseva.library.service;

import com.degaltseva.library.entity.Book;

public interface BookService {
    void createBook(Long id, String title, String author, int year);
    Book findById(Long id);
    void deleteById(Long id);
    void updateBookData(Long id, String title, String author, int year, boolean isAvailable);
}
