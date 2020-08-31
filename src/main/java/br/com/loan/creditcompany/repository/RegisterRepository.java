package br.com.loan.creditcompany.repository;

import br.com.loan.creditcompany.model.entity.RegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepository extends JpaRepository<RegisterEntity,Long> {
}
