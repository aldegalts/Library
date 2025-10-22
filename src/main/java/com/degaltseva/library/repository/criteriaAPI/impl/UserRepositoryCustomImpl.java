package com.degaltseva.library.repository.criteriaAPI.impl;

import com.degaltseva.library.entity.UserEntity;
import com.degaltseva.library.entity.ReservationEntity;
import com.degaltseva.library.repository.criteriaAPI.UserRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Реализация кастомного репозитория для пользователей с использованием Criteria API.
 */
@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserEntity> findUsersByBookId(Long bookId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query = cb.createQuery(UserEntity.class);

        Root<ReservationEntity> reservation = query.from(ReservationEntity.class);
        Join<ReservationEntity, UserEntity> userJoin = reservation.join("user", JoinType.INNER);
        Join<ReservationEntity, ?> bookJoin = reservation.join("book", JoinType.INNER);

        Predicate bookPredicate = cb.equal(bookJoin.get("bookId"), bookId);
        query.select(userJoin).where(bookPredicate).distinct(true);

        return entityManager.createQuery(query).getResultList();
    }
}
