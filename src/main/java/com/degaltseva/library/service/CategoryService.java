package com.degaltseva.library.service;

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
}
