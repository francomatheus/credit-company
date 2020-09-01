package br.com.loan.creditcompany.model.form;

import br.com.loan.creditcompany.model.DTO.RegisterDTO;
import br.com.loan.creditcompany.model.entity.RegisterEntity;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {

    @NotNull
    private String username;

    @NotNull
    private String email;

    private RegisterEntity registerEntity;

    private List<UserRoleForm> roles;

}
