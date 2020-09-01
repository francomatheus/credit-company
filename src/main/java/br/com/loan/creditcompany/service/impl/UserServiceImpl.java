package br.com.loan.creditcompany.service.impl;

import br.com.loan.creditcompany.model.DTO.AddressDTO;
import br.com.loan.creditcompany.model.DTO.RegisterDTO;
import br.com.loan.creditcompany.model.DTO.UserDTO;
import br.com.loan.creditcompany.model.DTO.UserRoleDTO;
import br.com.loan.creditcompany.model.entity.UserEntity;
import br.com.loan.creditcompany.model.entity.UserRoleEntity;
import br.com.loan.creditcompany.model.form.UserForm;
import br.com.loan.creditcompany.model.form.UserRoleForm;
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
    public List<UserDTO> getAllUser() {
        List<UserEntity> userEntities = Optional.ofNullable(userRepository.findAll()).orElseGet(() -> new ArrayList<>());

        return converterListOfUserEntityToListOfUserDTO(userEntities);

    }

    @Override
    public UserDTO getOneUser(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found user with id: ".concat(id.toString())));
        return converterUserEntityToUserDTO(userEntity);
    }

    @Override
    public AddressDTO getAddress(Long id) {
        Optional<UserEntity> userById = userRepository.findById(id);
        if (userById.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found user with id: ".concat(id.toString()));
        }

        UserEntity userEntity = userById.get();
        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setId(userEntity.getRegisterEntity().getAddress().getId());
        addressDTO.setStreet(userEntity.getRegisterEntity().getAddress().getStreet());
        addressDTO.setNumber(userEntity.getRegisterEntity().getAddress().getNumber());
        addressDTO.setComplements(userEntity.getRegisterEntity().getAddress().getComplements());
        addressDTO.setZipCode(userEntity.getRegisterEntity().getAddress().getZipCode());
        addressDTO.setCity(userEntity.getRegisterEntity().getAddress().getCity());
        addressDTO.setState(userEntity.getRegisterEntity().getAddress().getState());
        addressDTO.setCountry(userEntity.getRegisterEntity().getAddress().getCountry());

        return  addressDTO;
    }

    @Override
    public RegisterDTO getRegister(Long id) {
        Optional<UserEntity> userById = userRepository.findById(id);
        if (userById.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Not found id!!");
        }

        UserEntity userEntity = userById.get();
        RegisterDTO registerDTO = new RegisterDTO();

        registerDTO.setId(userEntity.getRegisterEntity().getId());
        registerDTO.setFullname(userEntity.getRegisterEntity().getFullname());
        registerDTO.setCpf(userEntity.getRegisterEntity().getCpf());
        registerDTO.setRg(userEntity.getRegisterEntity().getRg());
        registerDTO.setOccupation(userEntity.getRegisterEntity().getOccupation());
        registerDTO.setTelephone(userEntity.getRegisterEntity().getTelephone());
        registerDTO.setCellphone(userEntity.getRegisterEntity().getCellphone());
        registerDTO.setCivilStatus(userEntity.getRegisterEntity().getCivilStatus());
        registerDTO.setAge(userEntity.getRegisterEntity().getAge());
        registerDTO.setEducationLevel(userEntity.getRegisterEntity().getEducationLevel());
        registerDTO.setSalary(userEntity.getRegisterEntity().getSalary());
        registerDTO.setPartner(userEntity.getRegisterEntity().getPartner());

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(userEntity.getRegisterEntity().getAddress().getId());
        addressDTO.setStreet(userEntity.getRegisterEntity().getAddress().getStreet());
        addressDTO.setNumber(userEntity.getRegisterEntity().getAddress().getNumber());
        addressDTO.setComplements(userEntity.getRegisterEntity().getAddress().getComplements());
        addressDTO.setZipCode(userEntity.getRegisterEntity().getAddress().getZipCode());
        addressDTO.setCity(userEntity.getRegisterEntity().getAddress().getCity());
        addressDTO.setState(userEntity.getRegisterEntity().getAddress().getState());
        addressDTO.setCountry(userEntity.getRegisterEntity().getAddress().getCountry());

        registerDTO.setAddress(addressDTO);
        return registerDTO;
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
        userEntity.setRegisterEntity(userForm.getRegisterEntity());

        UserEntity UserUpdated = userRepository.save(userEntity);

        return converterUserEntityToUserDTO(UserUpdated);
    }
    @Override
    public void deleteUser(Long id) {
        UserEntity userEntity = Optional.ofNullable(userRepository.findById(id)).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found User with id: ".concat(id.toString()))).get();

        userRepository.delete(userEntity);
    }

    @Override
    public UserForm converterUserDTOToUserForm(UserDTO userDTO){

        UserForm userForm = new UserForm();

        userForm.setEmail(userDTO.getEmail());
        userForm.setUsername(userDTO.getUsername());
        userForm.setRegisterEntity(userDTO.getRegisterDTO());

        List<UserRoleForm> collect = userDTO.getRoles().stream()
                .map(userRoleDTO -> {
                    UserRoleForm userRoleForm = new UserRoleForm();
                    userRoleForm.setRole(userRoleDTO.getRole());
                    return userRoleForm;

                }).collect(Collectors.toList());

        userForm.setRoles(collect);

        return userForm;

    }

    @Override
    public UserEntity getOneUserAndRetornUserEntity(Long id) {
        Optional<UserEntity> byId = userRepository.findById(id);
        if (byId.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Not found id!!");
        }

        return byId.get();
    }

/*    @Override
    public UserEntity converterUserDTOToUserEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();

        userEntity.setEmail(userDTO.getEmail());
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setRegisterEntity(userDTO.getRegisterDTO());

        List<UserRoleEntity> collect = userDTO.getRoles().stream()
                .map(userRoleDTO -> {
                    UserRoleEntity roleEntity = new UserRoleEntity();
                    roleEntity.setId(userRoleDTO.getId());
                    roleEntity.setRole(userRoleDTO.getRole());
                    return roleEntity;

                }).collect(Collectors.toList());

        userEntity.setRoles(collect);

        return userEntity;
    }*/

    private List<UserDTO> converterListOfUserEntityToListOfUserDTO(List<UserEntity> userEntities) {
        return userEntities.stream()
                .map(userEntity -> {
                    UserDTO userDTO = new UserDTO();
                    userDTO.setId(userEntity.getId());
                    userDTO.setEmail(userEntity.getEmail());
                    userDTO.setUsername(userEntity.getUsername());
                    userDTO.setRegisterDTO(userEntity.getRegisterEntity());

                    List<UserRoleDTO> collect = userEntity.getRoles().stream()
                            .map(userRoleEntity -> {
                                UserRoleDTO userRoleDTO = new UserRoleDTO();
                                userRoleDTO.setId(userRoleEntity.getId());
                                userRoleDTO.setRole(userRoleEntity.getRole());
                                return userRoleDTO;
                            }).collect(Collectors.toList());

                    userDTO.setRoles(collect);
                    return userDTO;
                }).collect(Collectors.toList());
    }

    @Override
    public UserDTO converterUserEntityToUserDTO(UserEntity userEntity) {
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
        userEntity.setPassword(userEntity.getPassword());
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
