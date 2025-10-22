package com.degaltseva.library.repository.criteriaAPI.impl;

import com.degaltseva.library.entity.BookEntity;
import com.degaltseva.library.repository.criteriaAPI.BookRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Реализация кастомного репозитория для книг с использованием Criteria API.
 */
@Repository
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BookEntity> findByTitleAndYearPublishedBetween(String title, int startYear, int endYear) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BookEntity> query = cb.createQuery(BookEntity.class);
        Root<BookEntity> book = query.from(BookEntity.class);

        Predicate titlePredicate = cb.equal(book.get("title"), title);
        Predicate yearPredicate = cb.between(book.get("yearPublished"), startYear, endYear);

        query.select(book).where(cb.and(titlePredicate, yearPredicate));

        return entityManager.createQuery(query).getResultList();
    }
}
