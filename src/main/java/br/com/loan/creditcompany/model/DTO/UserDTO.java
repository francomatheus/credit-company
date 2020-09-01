package br.com.loan.creditcompany.model.DTO;

import br.com.loan.creditcompany.model.entity.RegisterEntity;
import br.com.loan.creditcompany.model.entity.UserRoleEntity;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String email;

    private RegisterEntity registerDTO;

    private List<UserRoleDTO> roles;
}
