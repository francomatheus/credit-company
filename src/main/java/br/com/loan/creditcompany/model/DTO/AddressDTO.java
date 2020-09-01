package br.com.loan.creditcompany.model.DTO;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private Long id;

    private String street;

    private String number;

    private String complements;

    private String zipCode;

    private String city;

    private String state;

    private String country;
}
