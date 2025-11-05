package com.degaltseva.library.service.impl;

import com.degaltseva.library.entity.BookEntity;
import com.degaltseva.library.entity.CategoryEntity;
import com.degaltseva.library.repository.BookRepository;
import com.degaltseva.library.repository.CategoryRepository;
import com.degaltseva.library.service.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

/**
 * Реализация сервиса для управления категориями.
 * <p>
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, BookRepository bookRepository) {
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public void deleteCategoryWithBooks(Long categoryId) {
        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Категория не найдена"));

        List<BookEntity> books = (List<BookEntity>) bookRepository.findAll();
        for (BookEntity book : books) {
            if (book.getCategory() != null && book.getCategory().getCategoryId().equals(categoryId)) {
                bookRepository.delete(book);
            }
        }

        categoryRepository.delete(category);
    }

    @Override
    public List<CategoryEntity> getAllCategories() {
        return (List<CategoryEntity>) categoryRepository.findAll();
    }

    @Override
    public CategoryEntity getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public CategoryEntity createCategory(CategoryEntity category) {
        return categoryRepository.save(category);
    }

    @Override
    public CategoryEntity updateCategory(Long id, CategoryEntity updatedCategory) {
        return categoryRepository.findById(id)
                .map(existing -> {
                    existing.setCategoryName(updatedCategory.getCategoryName());
                    existing.setDescription(updatedCategory.getDescription());
                    return categoryRepository.save(existing);
                })
                .orElse(null);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
