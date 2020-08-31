package br.com.loan.creditcompany.model.form;

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
    private String password;
    @NotNull
    private String email;

    private List<UserRoleForm> roles;
}
