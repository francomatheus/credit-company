package br.com.loan.creditcompany.model.form;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressForm {

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
