package br.com.loan.creditcompany.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {

    private Long id;
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
    private AddressDTO address;

}
