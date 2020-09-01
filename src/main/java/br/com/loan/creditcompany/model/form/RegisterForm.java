package br.com.loan.creditcompany.model.form;

import br.com.loan.creditcompany.model.DTO.AddressDTO;
import br.com.loan.creditcompany.model.entity.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterForm {


    private String fullname;
    private String cpf;
    private String rg;
    private String occupation;
    private String telephone;
    private String cellphone;
    private String civilStatus;
    private Integer age;
    private String educationLevel;
    private BigDecimal salary;
    private String partner;
    private AddressForm address;


}
