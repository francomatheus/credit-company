package br.com.loan.creditcompany.service;

import br.com.loan.creditcompany.model.DTO.AddressDTO;
import br.com.loan.creditcompany.model.form.AddressForm;

import java.util.List;

public interface AddressService {

    List<AddressDTO> getAllAddress();

    AddressDTO getOneAddress(Long id);

    AddressDTO getAddressByUserId(Long userId);

    AddressDTO saveAddress(AddressForm addressForm);

    AddressDTO updateAddress(Long id , AddressForm addressForm);

    void deleteAddress(Long id);

}
