package com.degaltseva.library.controller;

import com.degaltseva.library.entity.BookEntity;
import com.degaltseva.library.entity.UserEntity;

import com.degaltseva.library.repository.criteriaAPI.BookRepositoryCustom;
import com.degaltseva.library.repository.criteriaAPI.UserRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для работы с кастомными запросами через Criteria API.
 * <p>
 */
@RestController
@RequestMapping("/api/criteria")
public class CriteriaApiController {

    private final BookRepositoryCustom bookRepositoryCustom;
    private final UserRepositoryCustom userRepositoryCustom;

    @Autowired
    public CriteriaApiController(BookRepositoryCustom bookRepositoryCustom,
                                 UserRepositoryCustom userRepositoryCustom) {
        this.bookRepositoryCustom = bookRepositoryCustom;
        this.userRepositoryCustom = userRepositoryCustom;
    }

    /**
     * Найти книги по названию и диапазону годов публикации.
     * Пример запроса:
     * GET /api/criteria/books?title=Мастер%20и%20Маргарита&startYear=1960&endYear=1980
     */
    @GetMapping("/books")
    public List<BookEntity> findBooksByTitleAndYear(
            @RequestParam String title,
            @RequestParam int startYear,
            @RequestParam int endYear) {
        return bookRepositoryCustom.findByTitleAndYearPublishedBetween(title, startYear, endYear);
    }

    /**
     * Найти всех пользователей, которые бронировали книгу по её ID.
     * Пример запроса:
     * GET /api/criteria/users?bookId=5
     */
    @GetMapping("/users")
    public List<UserEntity> findUsersByBookId(@RequestParam Long bookId) {
        return userRepositoryCustom.findUsersByBookId(bookId);
    }
}
