package com.degaltseva.library.repository;

import com.degaltseva.library.entity.ReservationStatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы со статусами бронирования книг.
 * <p>
 */
@Repository
public interface ReservationStatusRepository extends CrudRepository<ReservationStatusEntity, Long> {
}
