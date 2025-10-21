package com.degaltseva.library.repository;

import com.degaltseva.library.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Репозиторий для работы с книгами.
 * <p>
 * Расширяет CrudRepository и добавляет метод поиска по названию и диапазону лет публикации.
 */
@Repository
public interface BookRepository extends CrudRepository<BookEntity, Long> {

    /**
     * Находит книги по названию и диапазону годов публикации.
     * <p>
     * Используется стратегия Query Lookup (ключевые слова And, Between).
     *
     * @param title     название книги
     * @param startYear начальный год
     * @param endYear   конечный год
     * @return список книг
     */
    List<BookEntity> findByTitleAndYearPublishedBetween(String title, int startYear, int endYear);
}
