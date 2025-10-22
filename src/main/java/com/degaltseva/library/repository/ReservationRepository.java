package com.degaltseva.library.repository;

import com.degaltseva.library.entity.ReservationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с бронированием книг.
 * <p>
 */
@Repository
public interface ReservationRepository extends CrudRepository<ReservationEntity, Long> {
}
