package com.degaltseva.library.repository;

/**
 * Базовый интерфейс для CRUD-операций с сущностями.
 * <p>
 * Возможности: создание, чтение, обновление и удаление сущностей.
 */
public interface CrudRepository<T, ID> {
    void create(T entity);
    T read(ID id);
    void update(T entity);
    void delete(ID id);
}

