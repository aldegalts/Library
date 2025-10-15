package com.degaltseva.library.repository;

import com.degaltseva.library.entity.Book;

/**
 * Репозиторий для CRUD-операций с книгами.
 * <p>
 * Возможности: создание, чтение, обновление и удаление книг.
 */
public interface BookCrudRepository extends CrudRepository<Book, Long> {

}
