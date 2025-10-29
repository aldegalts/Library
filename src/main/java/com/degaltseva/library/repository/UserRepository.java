package com.degaltseva.library.repository;

import com.degaltseva.library.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Репозиторий для работы с пользователями.
 * <p>
 * Расширяет CrudRepository и добавляет метод поиска пользователей по книгам через бронирования.
 */
@RepositoryRestResource
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    /**
     * Находит всех пользователей, которые бронировали книгу с заданным ID.
     * <p>
     * Реализовано через JPQL-запрос с использованием связанной сущности ReservationEntity.
     *
     * @param bookId идентификатор книги
     * @return список пользователей
     */
    @Query("SELECT r.user FROM ReservationEntity r WHERE r.book.bookId = :bookId")
    List<UserEntity> findUsersByBookId(Long bookId);
}
