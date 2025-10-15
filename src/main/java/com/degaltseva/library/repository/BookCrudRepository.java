package com.degaltseva.library.repository;

import com.degaltseva.library.entity.Book;

/**
 * Репозиторий для CRUD-операций с книгами.
 *
 * Возможности: создание, чтение, обновление и удаление книг.
 */
public interface BookCrudRepository extends CrudRepository<Book, Long> {

}
