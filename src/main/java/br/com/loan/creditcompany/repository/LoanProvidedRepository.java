package br.com.loan.creditcompany.repository;

import br.com.loan.creditcompany.model.entity.LoanProvidedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanProvidedRepository extends JpaRepository<LoanProvidedEntity,Long> {
}
