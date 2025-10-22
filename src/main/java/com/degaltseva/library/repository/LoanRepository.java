package com.degaltseva.library.repository;

import com.degaltseva.library.entity.LoanEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с арендой книг.
 * <p>
 */
@Repository
public interface LoanRepository extends CrudRepository<LoanEntity, Long> {
}
