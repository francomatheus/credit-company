package br.com.loan.creditcompany.model.form;

import br.com.loan.creditcompany.model.DTO.AddressDTO;
import br.com.loan.creditcompany.model.entity.AddressEntity;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterForm {

    @NotNull
    private String fullname;
    @NotNull
    private String cpf;
    @NotNull
    private String rg;
    private String occupation;
    @NotNull
    private String telephone;
    private String cellphone;
    private String civilStatus;
    private Integer age;
    private String educationLevel;
    @NotNull
    private BigDecimal salary;
    private String partner;
    private AddressForm address;


}
