package com.degaltseva.library.service;

import com.degaltseva.library.entity.CategoryEntity;

import java.util.List;

/**
 * Сервис для работы с категориями и связанными книгами.
 * Обеспечивает транзакционную целостность данных.
 */
public interface CategoryService {

    /**
     * Удаляет категорию и все книги, принадлежащие ей, в одной транзакции.
     *
     * @param categoryId идентификатор категории
     */
    void deleteCategoryWithBooks(Long categoryId);

    List<CategoryEntity> getAllCategories();

    CategoryEntity getCategoryById(Long id);

    CategoryEntity createCategory(CategoryEntity category);

    CategoryEntity updateCategory(Long id, CategoryEntity updatedCategory);

    void deleteCategory(Long id);
}
