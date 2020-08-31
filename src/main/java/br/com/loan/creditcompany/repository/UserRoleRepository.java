package br.com.loan.creditcompany.repository;

import br.com.loan.creditcompany.model.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    UserRoleEntity findByRole(String role);

}
