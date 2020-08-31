package br.com.loan.creditcompany.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {

    private Long id;
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
    @OneToOne
    private AddressDTO address;

}
