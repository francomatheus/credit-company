package br.com.loan.creditcompany.repository;

import br.com.loan.creditcompany.model.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}
