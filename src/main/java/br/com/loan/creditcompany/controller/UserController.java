package br.com.loan.creditcompany.controller;

import br.com.loan.creditcompany.model.DTO.UserDTO;
import br.com.loan.creditcompany.model.form.UserForm;
import br.com.loan.creditcompany.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/v0/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    private ResponseEntity<List<UserDTO>> getAllUser(){
        List<UserDTO> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }

    @GetMapping("/{id}")
    private ResponseEntity<UserDTO> getOneUser(@PathVariable Long id){
        UserDTO oneUser = userService.getOneUser(id);
        return ResponseEntity.ok(oneUser);
    }

    @PostMapping
    private ResponseEntity<UserDTO> saveUser(@RequestBody UserForm userForm) throws URISyntaxException {
        UserDTO userDTO = userService.saveUser(userForm);
        return ResponseEntity.created(new URI("/v0/user/".concat(userDTO.getId().toString()))).body(userDTO);
    }

    @PutMapping("/{id}")
    private ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserForm userForm){
        UserDTO userDTO = userService.updateUser(id, userForm);
        return ResponseEntity.accepted().body(userDTO);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Object> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

}
