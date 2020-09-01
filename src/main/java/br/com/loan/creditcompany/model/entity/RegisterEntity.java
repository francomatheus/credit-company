package br.com.loan.creditcompany.model.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "register")
public class RegisterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotNull
    private String fullname;
    @Column(unique = true)
    @NotNull
    private String cpf;
    @Column(unique = true)
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
    @OneToOne
    private AddressEntity address;
    @OneToOne
    private UserEntity userEntity;

}
