package com.degaltseva.library.repository;

import com.degaltseva.library.entity.ReservationStatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Репозиторий для работы со статусами бронирования книг.
 * <p>
 */
@RepositoryRestResource
public interface ReservationStatusRepository extends CrudRepository<ReservationStatusEntity, Long> {
}
