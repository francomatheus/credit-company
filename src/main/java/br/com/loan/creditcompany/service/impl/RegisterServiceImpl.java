package br.com.loan.creditcompany.service.impl;

import br.com.loan.creditcompany.model.DTO.AddressDTO;
import br.com.loan.creditcompany.model.DTO.RegisterDTO;
import br.com.loan.creditcompany.model.DTO.UserDTO;
import br.com.loan.creditcompany.model.entity.AddressEntity;
import br.com.loan.creditcompany.model.entity.RegisterEntity;
import br.com.loan.creditcompany.model.entity.UserEntity;
import br.com.loan.creditcompany.model.form.RegisterForm;
import br.com.loan.creditcompany.model.form.UserForm;
import br.com.loan.creditcompany.repository.RegisterRepository;
import br.com.loan.creditcompany.service.AddressService;
import br.com.loan.creditcompany.service.RegisterService;
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
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Override
    public List<RegisterDTO> getAllRegisters() {
        List<RegisterEntity> registerEntities = Optional.ofNullable(registerRepository.findAll()).orElseGet(() -> new ArrayList<>());
        List<RegisterDTO> registerDTOS = converterListOfRegisterEntityToListOfRegisterDTO(registerEntities);
        return registerDTOS;
    }

    @Override
    public RegisterDTO getOneRegister(Long registerId) {
        Optional<RegisterEntity> registerById = registerRepository.findById(registerId);
        if (registerById.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Id not found!!");
        }

        return converterRegisterEntityTORegisterDTO(registerById.get());
    }

    @Override
    public RegisterDTO getRegisterByUserId(Long id) {
        return userService.getRegister(id);
    }


    @Override
    public RegisterDTO saveRegister(Long userId, RegisterForm registerForm) {
        AddressDTO addressDTO = saveAddress(registerForm);
        RegisterEntity registerEntity = converterRegisterFormToRegisterEntity(registerForm, addressDTO);
        RegisterEntity registerSaved = registerRepository.save(registerEntity);

        updateUserWithRegister(userId, registerSaved);
        return converterRegisterEntityTORegisterDTO(registerSaved);
    }

    private void updateUserWithRegister(Long userId, RegisterEntity registerDTO) {
        UserDTO oneUser = userService.getOneUser(userId);

        oneUser.setRegisterDTO(registerDTO);
        UserForm userForm = userService.converterUserDTOToUserForm(oneUser);
        userService.updateUser(userId, userForm);

    }


    @Override
    public RegisterDTO updateRegister(Long registerId, RegisterForm registerForm) {
        Optional<RegisterEntity> registerById = registerRepository.findById(registerId);

        if (registerById.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Id not found!!");
        }
        RegisterEntity registerEntity = registerById.get();

        registerEntity.setId(registerEntity.getId());
        registerEntity.setFullname(registerForm.getFullname());
        registerEntity.setCpf(registerForm.getCpf());
        registerEntity.setRg(registerForm.getRg());
        registerEntity.setOccupation(registerForm.getOccupation());
        registerEntity.setTelephone(registerForm.getTelephone());
        registerEntity.setCellphone(registerForm.getCellphone());
        registerEntity.setCivilStatus(registerForm.getCivilStatus());
        registerEntity.setAge(registerForm.getAge());
        registerEntity.setEducationLevel(registerForm.getEducationLevel());
        registerEntity.setSalary(registerForm.getSalary());
        registerEntity.setPartner(registerForm.getPartner());
        registerEntity.setAddress(registerEntity.getAddress());

        RegisterEntity registerUpdated = registerRepository.save(registerEntity);

        return converterRegisterEntityTORegisterDTO(registerUpdated);
    }

    @Override
    public void deleteRegister(Long id) {
        Optional<RegisterEntity> registerById = registerRepository.findById(id);

        if (registerById.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Id not found!!");
        }
        registerRepository.deleteById(id);
    }

    private List<RegisterDTO> converterListOfRegisterEntityToListOfRegisterDTO(List<RegisterEntity> registerEntities) {
        return registerEntities.stream()
                .map(registerEntity -> {
                    RegisterDTO registerDTO = new RegisterDTO();
                    registerDTO.setId(registerEntity.getId());
                    registerDTO.setFullname(registerEntity.getFullname());
                    registerDTO.setCpf(registerEntity.getCpf());
                    registerDTO.setRg(registerEntity.getRg());
                    registerDTO.setOccupation(registerEntity.getOccupation());
                    registerDTO.setTelephone(registerEntity.getTelephone());
                    registerDTO.setCellphone(registerEntity.getCellphone());
                    registerDTO.setCivilStatus(registerEntity.getCivilStatus());
                    registerDTO.setAge(registerEntity.getAge());
                    registerDTO.setEducationLevel(registerEntity.getEducationLevel());
                    registerDTO.setSalary(registerEntity.getSalary());
                    registerDTO.setPartner(registerEntity.getPartner());

                    AddressDTO addressDTO = new AddressDTO();
                    addressDTO.setId(registerEntity.getAddress().getId());
                    addressDTO.setStreet(registerEntity.getAddress().getStreet());
                    addressDTO.setNumber(registerEntity.getAddress().getNumber());
                    addressDTO.setComplements(registerEntity.getAddress().getComplements());
                    addressDTO.setZipCode(registerEntity.getAddress().getZipCode());
                    addressDTO.setCity(registerEntity.getAddress().getCity());
                    addressDTO.setState(registerEntity.getAddress().getState());
                    addressDTO.setCountry(registerEntity.getAddress().getCountry());

                    registerDTO.setAddress(addressDTO);
                    return registerDTO;
                }).collect(Collectors.toList());
    }

    private RegisterDTO converterRegisterEntityTORegisterDTO(RegisterEntity registerEntity) {
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setId(registerEntity.getId());
        registerDTO.setFullname(registerEntity.getFullname());
        registerDTO.setCpf(registerEntity.getCpf());
        registerDTO.setRg(registerEntity.getRg());
        registerDTO.setOccupation(registerEntity.getOccupation());
        registerDTO.setTelephone(registerEntity.getTelephone());
        registerDTO.setCellphone(registerEntity.getCellphone());
        registerDTO.setCivilStatus(registerEntity.getCivilStatus());
        registerDTO.setAge(registerEntity.getAge());
        registerDTO.setEducationLevel(registerEntity.getEducationLevel());
        registerDTO.setSalary(registerEntity.getSalary());
        registerDTO.setPartner(registerEntity.getPartner());

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(registerEntity.getAddress().getId());
        addressDTO.setStreet(registerEntity.getAddress().getStreet());
        addressDTO.setNumber(registerEntity.getAddress().getNumber());
        addressDTO.setComplements(registerEntity.getAddress().getComplements());
        addressDTO.setZipCode(registerEntity.getAddress().getZipCode());
        addressDTO.setCity(registerEntity.getAddress().getCity());
        addressDTO.setState(registerEntity.getAddress().getState());
        addressDTO.setCountry(registerEntity.getAddress().getCountry());

        registerDTO.setAddress(addressDTO);

        return registerDTO;
    }

    private RegisterEntity converterRegisterFormToRegisterEntity(RegisterForm registerForm, AddressDTO addressDTO) {
        RegisterEntity registerEntity = new RegisterEntity();
        //registerEntity.setId(registerForm.getId());
        registerEntity.setFullname(registerForm.getFullname());
        registerEntity.setCpf(registerForm.getCpf());
        registerEntity.setRg(registerForm.getRg());
        registerEntity.setOccupation(registerForm.getOccupation());
        registerEntity.setTelephone(registerForm.getTelephone());
        registerEntity.setCellphone(registerForm.getCellphone());
        registerEntity.setCivilStatus(registerForm.getCivilStatus());
        registerEntity.setAge(registerForm.getAge());
        registerEntity.setEducationLevel(registerForm.getEducationLevel());
        registerEntity.setSalary(registerForm.getSalary());
        registerEntity.setPartner(registerForm.getPartner());

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(addressDTO.getId());
        addressEntity.setStreet(addressDTO.getStreet());
        addressEntity.setNumber(addressDTO.getNumber());
        addressEntity.setComplements(addressDTO.getComplements());
        addressEntity.setZipCode(addressDTO.getZipCode());
        addressEntity.setCity(addressDTO.getCity());
        addressEntity.setState(addressDTO.getState());
        addressEntity.setCountry(addressDTO.getCountry());

        registerEntity.setAddress(addressEntity);
        return registerEntity;

    }

    private AddressDTO saveAddress(RegisterForm registerForm) {
        AddressDTO addressDTO = addressService.saveAddress(registerForm.getAddress());
        return addressDTO;
    }

}
