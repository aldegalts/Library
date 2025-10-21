package com.degaltseva.library.service.impl;

import com.degaltseva.library.entity.BookEntity;
import com.degaltseva.library.entity.CategoryEntity;
import com.degaltseva.library.repository.BookRepository;
import com.degaltseva.library.repository.CategoryRepository;
import com.degaltseva.library.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void deleteCategoryWithBooks_positive() {
        CategoryEntity category = new CategoryEntity("Science Fiction", "Sci-Fi books");
        category = categoryRepository.save(category);

        BookEntity book1 = new BookEntity("Book1", "Author1", "pub1", 1965, category, 10, 9);
        BookEntity book2 = new BookEntity("Book2", "Author2", "pub2", 1984, category, 5, 5);
        bookRepository.save(book1);
        bookRepository.save(book2);

        categoryService.deleteCategoryWithBooks(category.getCategoryId());

        Optional<CategoryEntity> deletedCategory = categoryRepository.findById(category.getCategoryId());
        assertThat(deletedCategory).isEmpty();

        List<BookEntity> books = (List<BookEntity>) bookRepository.findAll();
        assertThat(books).doesNotContain(book1, book2);
    }

    @Test
    void deleteCategoryWithBooks_negative_categoryNotFound() {
        Long invalidCategoryId = 999L;

        assertThrows(IllegalArgumentException.class,
                () -> categoryService.deleteCategoryWithBooks(invalidCategoryId));

        List<CategoryEntity> categories = (List<CategoryEntity>) categoryRepository.findAll();
        List<BookEntity> books = (List<BookEntity>) bookRepository.findAll();
        assertThat(categories).isNotEmpty();
        assertThat(books).isNotEmpty();
    }
}
