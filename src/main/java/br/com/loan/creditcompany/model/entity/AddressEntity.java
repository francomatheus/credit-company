package br.com.loan.creditcompany.model.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String street;
    @NotNull
    private String number;
    private String complements;
    private String zipCode;
    @NotNull
    private String city;
    @NotNull
    private String state;
    @NotNull
    private String country;

}
