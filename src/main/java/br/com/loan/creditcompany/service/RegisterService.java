package br.com.loan.creditcompany.service;

import br.com.loan.creditcompany.model.DTO.RegisterDTO;
import br.com.loan.creditcompany.model.form.RegisterForm;

import java.util.List;

public interface RegisterService {

    List<RegisterDTO> getAllRegisters();

    RegisterDTO getOneRegister(Long registerId);

    RegisterDTO getRegisterByUserId(Long id);

    RegisterDTO saveRegister(RegisterForm registerForm);

    RegisterDTO updateRegister(Long registerId , RegisterForm registerForm);

    void deleteRegister(Long id);

}
