package com.degaltseva.library.repository;

import com.degaltseva.library.entity.User;

/**
 * Репозиторий для CRUD-операций с пользователями.
 *
 * Возможности: создание, чтение, обновление и удаление пользователей.
 */
public interface UserCrudRepository extends CrudRepository<User, Long> {

}
