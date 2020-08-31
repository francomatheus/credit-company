package br.com.loan.creditcompany.service.impl;

import br.com.loan.creditcompany.model.DTO.UserRoleDTO;
import br.com.loan.creditcompany.model.entity.UserRoleEntity;
import br.com.loan.creditcompany.model.form.UserRoleForm;
import br.com.loan.creditcompany.repository.UserRoleRepository;
import br.com.loan.creditcompany.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;


    @Override
    public List<UserRoleDTO> getAllRoles() {
        List<UserRoleEntity> allRoles = userRoleRepository.findAll();
        return converterListOfUserRoleEntityToListOfUserRoleDTO(allRoles);
    }

    @Override
    public UserRoleDTO getOneRole(Long id) {
        UserRoleEntity userRoleEntity = userRoleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found id: ".concat(id.toString())));

        return converterUserRoleEntityToUserRoleDTO(userRoleEntity);
    }



    @Override
    public UserRoleDTO saveRole(UserRoleForm userRoleForm) {
        UserRoleEntity userRoleEntity = converterUserRoleFormToUserRoleEntity(userRoleForm);

        UserRoleEntity roleSaved = userRoleRepository.save(userRoleEntity);

        return converterUserRoleEntityToUserRoleDTO(roleSaved);

    }

    @Override
    public UserRoleDTO updateRole(Long id, UserRoleForm userRoleForm) {
        UserRoleEntity userRoleEntity = userRoleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found id: ".concat(id.toString())));

        userRoleEntity.setId(userRoleEntity.getId());
        userRoleEntity.setRole(userRoleForm.getRole());

        UserRoleEntity roleUpdate = userRoleRepository.save(userRoleEntity);

        return converterUserRoleEntityToUserRoleDTO(roleUpdate);
    }

    @Override
    public void deleteRole(Long id) {
        UserRoleEntity userRoleEntity = Optional.ofNullable(userRoleRepository.findById(id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found id: ".concat(id.toString()))).get();
        userRoleRepository.deleteById(id);
    }

    private UserRoleDTO converterUserRoleEntityToUserRoleDTO(UserRoleEntity userRoleEntity) {
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setRole(userRoleEntity.getRole());
        userRoleDTO.setId(userRoleEntity.getId());

        return userRoleDTO;
    }

    private UserRoleEntity converterUserRoleFormToUserRoleEntity(UserRoleForm userRoleForm) {
        UserRoleEntity userRoleEntity = new UserRoleEntity();

        userRoleEntity.setRole(userRoleForm.getRole());

        return userRoleEntity;
    }

    private List<UserRoleDTO> converterListOfUserRoleEntityToListOfUserRoleDTO(List<UserRoleEntity> allRoles) {
        return Optional.ofNullable(allRoles).orElseGet(() -> new ArrayList<>()).stream()
                .map(userRoleEntity -> {
                    UserRoleDTO userRoleDTO = new UserRoleDTO();
                    userRoleDTO.setId(userRoleEntity.getId());
                    userRoleDTO.setRole(userRoleEntity.getRole());
                    return userRoleDTO;
                }).collect(Collectors.toList());
    }
}
