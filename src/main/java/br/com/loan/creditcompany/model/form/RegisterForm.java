package br.com.loan.creditcompany.model.form;

import br.com.loan.creditcompany.model.entity.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterForm {


    private String fullname;
    private String cpf;
    private String rg;
    private String occupation;
    private Integer telephone;
    private Integer cellphone;
    private String civilStatus;
    private Integer age;
    private String educationLevel;
    private Double salary;
    private String partner;
    private AddressEntity address;


}
