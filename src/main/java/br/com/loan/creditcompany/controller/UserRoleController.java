package br.com.loan.creditcompany.controller;

import br.com.loan.creditcompany.model.DTO.UserRoleDTO;
import br.com.loan.creditcompany.model.form.UserRoleForm;
import br.com.loan.creditcompany.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/v0/role")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping
    public ResponseEntity<List<UserRoleDTO>> getAllRoles(){
        List<UserRoleDTO> allRoles = userRoleService.getAllRoles();
        return ResponseEntity.ok(allRoles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRoleDTO> getOneRole(@PathVariable Long id){
        UserRoleDTO oneRole = userRoleService.getOneRole(id);
        return ResponseEntity.ok(oneRole);
    }

    @PostMapping
    public ResponseEntity<UserRoleDTO> saveRole(@RequestBody UserRoleForm userRoleForm) throws URISyntaxException {
        UserRoleDTO userRoleDTO = userRoleService.saveRole(userRoleForm);
        return ResponseEntity.created(new URI("/v0/role/".concat(userRoleDTO.getId().toString()))).body(userRoleDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRoleDTO> updateRole(@PathVariable Long id, @RequestBody UserRoleForm userRoleForm){
        UserRoleDTO userRoleDTO = userRoleService.updateRole(id, userRoleForm);
        return ResponseEntity.accepted().body(userRoleDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRole(@PathVariable Long id){
        userRoleService.deleteRole(id);
        return ResponseEntity.ok().build();
    }
}
