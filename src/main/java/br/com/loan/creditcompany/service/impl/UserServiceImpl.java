package br.com.loan.creditcompany.service.impl;

import br.com.loan.creditcompany.model.DTO.UserDTO;
import br.com.loan.creditcompany.model.DTO.UserRoleDTO;
import br.com.loan.creditcompany.model.entity.UserEntity;
import br.com.loan.creditcompany.model.entity.UserRoleEntity;
import br.com.loan.creditcompany.model.form.UserForm;
import br.com.loan.creditcompany.repository.UserRepository;
import br.com.loan.creditcompany.repository.UserRoleRepository;
import br.com.loan.creditcompany.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public List<UserEntity> getAllUser() {
        return Optional.ofNullable(userRepository.findAll()).orElseGet(() -> new ArrayList<>());
    }

    @Override
    public UserDTO getOneUser(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found user with id: ".concat(id.toString())));
        return converterUserEntityToUserDTO(userEntity);
    }

    @Override
    public UserDTO saveUser(UserForm userForm) {
        UserEntity userEntity = converterUserFormToUserEntity(userForm);
        UserEntity userSaved = userRepository.save(userEntity);
        return converterUserEntityToUserDTO(userSaved);
    }
    @Override
    public UserDTO updateUser(Long id, UserForm userForm) {

        UserEntity userEntity = Optional.ofNullable(userRepository.findById(id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found User with id: ".concat(id.toString()))).get();

        userEntity.setUsername(userForm.getUsername());
        userEntity.setRoles(userEntity.getRoles());
        userEntity.setPassword(userEntity.getPassword());
        userEntity.setEmail(userForm.getEmail());

        UserEntity UserUpdated = userRepository.save(userEntity);

        return converterUserEntityToUserDTO(UserUpdated);
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity userEntity = Optional.ofNullable(userRepository.findById(id)).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found User with id: ".concat(id.toString()))).get();

        userRepository.delete(userEntity);
    }

    private UserDTO converterUserEntityToUserDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();

        List<UserRoleDTO> userRoles = userEntity.getRoles().stream()
                .map(userRoleEntity -> {
                    UserRoleDTO userRoleDTO = new UserRoleDTO();
                    userRoleDTO.setId(userRoleEntity.getId());
                    userRoleDTO.setRole(userRoleEntity.getRole());
                    return userRoleDTO;
                }).collect(Collectors.toList());

        userDTO.setUsername(userEntity.getUsername());
        userDTO.setRoles(userRoles);
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setId(userEntity.getId());

        return userDTO;
    }

    private UserEntity converterUserFormToUserEntity(UserForm userForm) {
        UserEntity userEntity = new UserEntity();
        List<UserRoleEntity> userRoleEntities = new ArrayList<>();

        userEntity.setEmail(userForm.getEmail());
        userEntity.setPassword(userForm.getPassword());
        userEntity.setUsername(userForm.getUsername());


        Optional.ofNullable(userForm.getRoles()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Need to UserRole"))
                .stream()
                .map(userRoleForm -> {
                    Optional<UserRoleEntity> byRole = Optional.ofNullable(userRoleRepository.findByRole(userRoleForm.getRole()));
                    if (byRole.isPresent()){
                        userRoleEntities.add(byRole.get());
                        return userRoleEntities;
                    }else{

                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Role doesn't exist!!");
                    }
                }).collect(Collectors.toList());

        userEntity.setRoles(userRoleEntities);

        return userEntity;
    }


}
