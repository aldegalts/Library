package com.degaltseva.library.repository.criteriaAPI;

import com.degaltseva.library.entity.BookEntity;
import java.util.List;

/**
 * Кастомный репозиторий для работы с книгами через Criteria API.
 */
public interface BookRepositoryCustom {

    /**
     * Находит книги по названию и диапазону годов публикации.
     *
     * @param title название книги
     * @param startYear начальный год
     * @param endYear конечный год
     * @return список найденных книг
     */
    List<BookEntity> findByTitleAndYearPublishedBetween(String title, int startYear, int endYear);
}
