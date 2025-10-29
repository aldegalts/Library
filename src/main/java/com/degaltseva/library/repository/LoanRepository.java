package com.degaltseva.library.repository;

import com.degaltseva.library.entity.LoanEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Репозиторий для работы с арендой книг.
 * <p>
 */
@RepositoryRestResource
public interface LoanRepository extends CrudRepository<LoanEntity, Long> {
}
