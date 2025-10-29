package com.degaltseva.library.repository;

import com.degaltseva.library.entity.LoanStatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Репозиторий для работы со статусами аренды книг.
 * <p>
 */
@RepositoryRestResource
public interface LoanStatusRepository extends CrudRepository<LoanStatusEntity, Long> {
}
