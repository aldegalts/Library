package com.degaltseva.library.repository;

import com.degaltseva.library.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Репозиторий для работы с категориями книг.
 * <p>
 */
@RepositoryRestResource
public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
}
