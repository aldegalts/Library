package com.degaltseva.library.repository;

import com.degaltseva.library.entity.BookEntity;
import com.degaltseva.library.entity.CategoryEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private final Random random = new Random();

    @BeforeEach
    void cleanDatabase() {
        bookRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    /**
     * Проверяет, что метод корректно возвращает книги по названию и диапазону годов публикации.
     */
    @Test
    @Transactional
    void testFindByTitleAndYearPublishedBetween_positive() {
        // Подготовка
        CategoryEntity category = createAndSaveCategory();

        String commonTitle = randomTitle();
        createAndSaveBook(commonTitle, category, 2020);
        createAndSaveBook(commonTitle, category, 2021);

        // Действие
        List<BookEntity> foundBooks = bookRepository.findByTitleAndYearPublishedBetween(
                commonTitle, 2020, 2021
        );

        // Проверки
        Assertions.assertEquals(2, foundBooks.size());
        Assertions.assertTrue(foundBooks.stream().allMatch(b -> b.getTitle().equals(commonTitle)));
    }

    /**
     * Проверяет, что метод возвращает пустой список, если книги не найдены.
     */
    @Test
    @Transactional
    void testFindByTitleAndYearPublishedBetween_negative() {
        // Подготовка
        CategoryEntity category = createAndSaveCategory();
        createAndSaveBook(randomTitle(), category, 2019);

        // Действие
        List<BookEntity> foundBooks = bookRepository.findByTitleAndYearPublishedBetween(
                "NonExistingTitle", 2020, 2021
        );

        // Проверка
        Assertions.assertTrue(foundBooks.isEmpty());
    }

    private CategoryEntity createAndSaveCategory() {
        CategoryEntity category = new CategoryEntity(
                "Category_" + UUID.randomUUID(),
                "Description_" + UUID.randomUUID()
        );
        return categoryRepository.save(category);
    }

    private BookEntity createAndSaveBook(String title, CategoryEntity category, int year) {
        BookEntity book = new BookEntity(
                title,
                "Author_" + UUID.randomUUID(),
                "Publisher_" + UUID.randomUUID(),
                year,
                category,
                random.nextInt(10) + 1,
                random.nextInt(10) + 1
        );
        return bookRepository.save(book);
    }

    private String randomTitle() {
        return "Book_" + UUID.randomUUID();
    }
}
