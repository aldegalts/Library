package com.degaltseva.library.repository;

import com.degaltseva.library.entity.LoanStatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы со статусами аренды книг.
 * <p>
 */
@Repository
public interface LoanStatusRepository extends CrudRepository<LoanStatusEntity, Long> {
}
