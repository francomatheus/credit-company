package br.com.loan.creditcompany.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "register")
public class RegisterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private AddressEntity address;

}
