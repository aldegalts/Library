package com.degaltseva.library.repository.criteriaAPI.impl;

import com.degaltseva.library.entity.BookEntity;
import com.degaltseva.library.entity.CategoryEntity;
import com.degaltseva.library.repository.BookRepository;
import com.degaltseva.library.repository.CategoryRepository;
import com.degaltseva.library.repository.criteriaAPI.BookRepositoryCustom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class BookRepositoryCustomTest {

    @Autowired
    private BookRepositoryCustom bookRepositoryCustom;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void cleanDatabase() {
        bookRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    @Transactional
    void testFindByTitleAndYearPublishedBetween_positive() {
        // given
        CategoryEntity category = new CategoryEntity("Fiction", "Fiction description");
        category = categoryRepository.save(category);

        BookEntity book1 = new BookEntity("Test Book", "Author1", "Publisher1", 2020, category, 5, 5);
        BookEntity book2 = new BookEntity("Test Book", "Author2", "Publisher2", 2021, category, 3, 3);
        bookRepository.save(book1);
        bookRepository.save(book2);

        // when
        List<BookEntity> foundBooks = bookRepositoryCustom.findByTitleAndYearPublishedBetween(
                "Test Book", 2020, 2021
        );

        // then
        Assertions.assertEquals(2, foundBooks.size());
        Assertions.assertTrue(foundBooks.stream().allMatch(b -> b.getTitle().equals("Test Book")));
    }

    @Test
    @Transactional
    void testFindByTitleAndYearPublishedBetween_negative() {
        // given
        CategoryEntity category = new CategoryEntity("NonFiction", "NonFiction description");
        category = categoryRepository.save(category);

        BookEntity book = new BookEntity("Other Book", "Author", "Publisher", 2019, category, 2, 2);
        bookRepository.save(book);

        // when
        List<BookEntity> foundBooks = bookRepositoryCustom.findByTitleAndYearPublishedBetween(
                "Test Book", 2020, 2021
        );

        // then
        Assertions.assertTrue(foundBooks.isEmpty());
    }
}
