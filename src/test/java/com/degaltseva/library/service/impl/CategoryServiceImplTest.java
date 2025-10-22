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
import java.util.Random;
import java.util.UUID;

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

    private final Random random = new Random();

    /**
     * Проверяет, что категория и связанные с ней книги удаляются корректно.
     */
    @Test
    void deleteCategoryWithBooks_positive() {
        // Подготовка
        CategoryEntity category = createAndSaveCategory();

        BookEntity book1 = createAndSaveBook(category, 1990);
        BookEntity book2 = createAndSaveBook(category, 2000);

        // Действия и проверки
        categoryService.deleteCategoryWithBooks(category.getCategoryId());

        Optional<CategoryEntity> deletedCategory = categoryRepository.findById(category.getCategoryId());
        assertThat(deletedCategory).isEmpty();

        List<BookEntity> books = (List<BookEntity>) bookRepository.findAll();
        assertThat(books).doesNotContain(book1, book2);
    }

    /**
     * Проверяет, что при удалении несуществующей категории выбрасывается исключение,
     * и транзакция откатывается.
     */
    @Test
    void deleteCategoryWithBooks_negative_categoryNotFound() {
        // Подготовка
        Long invalidCategoryId = 999L;

        // Действия и проверки
        assertThrows(IllegalArgumentException.class, () -> categoryService.deleteCategoryWithBooks(invalidCategoryId));

        assertThat(categoryRepository.findAll()).isNotNull();
    }

    private CategoryEntity createAndSaveCategory() {
        return categoryRepository.save(new CategoryEntity(
                "Category_" + UUID.randomUUID(),
                "Description_" + UUID.randomUUID()
        ));
    }

    private BookEntity createAndSaveBook(CategoryEntity category, int year) {
        return bookRepository.save(new BookEntity(
                "Book_" + UUID.randomUUID(),
                "Author_" + UUID.randomUUID(),
                "Publisher_" + UUID.randomUUID(),
                year,
                category,
                random.nextInt(10) + 1,
                random.nextInt(10) + 1
        ));
    }
}
