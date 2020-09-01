package br.com.loan.creditcompany.repository;

import br.com.loan.creditcompany.model.entity.RequestCreditEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RequestCreditRepository extends JpaRepository<RequestCreditEntity, Long> {

    Optional<RequestCreditEntity> findByIdAndUserEntityId(Long id, Long userId);
    List<RequestCreditEntity> findAllByUserEntity_Id(Long userId);
}
