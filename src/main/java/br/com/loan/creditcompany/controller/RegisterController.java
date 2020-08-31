package br.com.loan.creditcompany.controller;

import br.com.loan.creditcompany.model.DTO.RegisterDTO;
import br.com.loan.creditcompany.model.form.RegisterForm;
import br.com.loan.creditcompany.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/v0/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @GetMapping
    public ResponseEntity<List<RegisterDTO>> getAllRegisters(){
        List<RegisterDTO> allRegisters = registerService.getAllRegisters();
        return ResponseEntity.ok(allRegisters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegisterDTO> getOneRegister(@PathVariable Long id){
        RegisterDTO oneRegister = registerService.getOneRegister(id);
        return ResponseEntity.ok(oneRegister);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<RegisterDTO> getRegisterByUserId(@PathVariable Long id){
        RegisterDTO registerByUserId = registerService.getRegisterByUserId(id);
        return ResponseEntity.ok(registerByUserId);
    }

    @PostMapping
    public ResponseEntity<RegisterDTO> createNewRegister(@RequestBody RegisterForm registerForm) throws URISyntaxException {
        RegisterDTO registerDTO = registerService.saveRegister(registerForm);
        return ResponseEntity.created(new URI("/v0/register/".concat(registerDTO.getId().toString()))).body(registerDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegisterDTO> updateRegiter(@PathVariable Long id, @RequestBody RegisterForm registerForm){
        RegisterDTO registerDTO = registerService.updateRegister(id, registerForm);
        return ResponseEntity.accepted().body(registerDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRegister(@PathVariable Long id){
        registerService.deleteRegister(id);
        return ResponseEntity.ok().build();
    }

}
