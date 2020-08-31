package br.com.loan.creditcompany.service;

import br.com.loan.creditcompany.model.DTO.UserRoleDTO;
import br.com.loan.creditcompany.model.form.UserRoleForm;

import java.util.List;

public interface UserRoleService {

    List<UserRoleDTO> getAllRoles();

    UserRoleDTO getOneRole(Long id);

    UserRoleDTO saveRole(UserRoleForm userRoleForm);

    UserRoleDTO updateRole(Long id, UserRoleForm userRoleForm);

    void deleteRole(Long id);
}
