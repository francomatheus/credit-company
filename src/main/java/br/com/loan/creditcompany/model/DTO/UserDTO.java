package br.com.loan.creditcompany.model.DTO;

import br.com.loan.creditcompany.model.entity.RegisterEntity;
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

    private String username;

    private String email;

    private RegisterEntity registerDTO;

    private List<UserRoleDTO> roles;
}
