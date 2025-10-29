package com.degaltseva.library.repository;

import com.degaltseva.library.entity.ReservationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Репозиторий для работы с бронированием книг.
 * <p>
 */
@RepositoryRestResource
public interface ReservationRepository extends CrudRepository<ReservationEntity, Long> {
}
