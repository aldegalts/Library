package com.degaltseva.library.repository;

import com.degaltseva.library.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с категориями книг.
 * <p>
 */
@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
}
