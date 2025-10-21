package com.degaltseva.library.repository.criteriaAPI;

import com.degaltseva.library.entity.UserEntity;
import java.util.List;

/**
 * Кастомный репозиторий для работы с пользователями через Criteria API.
 */
public interface UserRepositoryCustom {

    /**
     * Находит всех пользователей, которые бронировали книгу с заданным ID.
     *
     * @param bookId идентификатор книги
     * @return список пользователей
     */
    List<UserEntity> findUsersByBookId(Long bookId);
}
