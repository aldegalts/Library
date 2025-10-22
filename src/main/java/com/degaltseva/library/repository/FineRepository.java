package com.degaltseva.library.repository;

import com.degaltseva.library.entity.FineEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с нарушениями при аренде книги.
 * <p>
 */
@Repository
public interface FineRepository extends CrudRepository<FineEntity, Long> {
}
