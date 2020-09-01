package br.com.loan.creditcompany.service;

import br.com.loan.creditcompany.model.DTO.AddressDTO;
import br.com.loan.creditcompany.model.DTO.RegisterDTO;
import br.com.loan.creditcompany.model.DTO.UserDTO;
import br.com.loan.creditcompany.model.entity.UserEntity;
import br.com.loan.creditcompany.model.form.UserForm;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUser();

    UserDTO getOneUser(Long id);

    AddressDTO getAddress(Long id);

    RegisterDTO getRegister(Long id);

    UserDTO saveUser(UserForm userForm);

    UserDTO updateUser(Long id, UserForm userForm);

    void deleteUser(Long id);

    UserForm converterUserDTOToUserForm(UserDTO userDTO);

    UserEntity getOneUserAndRetornUserEntity(Long id);

    UserDTO converterUserEntityToUserDTO(UserEntity userEntity);

}
