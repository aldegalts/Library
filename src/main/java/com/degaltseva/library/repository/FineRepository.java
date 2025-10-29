package com.degaltseva.library.repository;

import com.degaltseva.library.entity.FineEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Репозиторий для работы с нарушениями при аренде книги.
 * <p>
 */
@RepositoryRestResource
public interface FineRepository extends CrudRepository<FineEntity, Long> {
}
