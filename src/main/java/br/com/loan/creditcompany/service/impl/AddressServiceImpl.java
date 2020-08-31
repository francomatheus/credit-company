package br.com.loan.creditcompany.service.impl;

import br.com.loan.creditcompany.model.DTO.AddressDTO;
import br.com.loan.creditcompany.model.entity.AddressEntity;
import br.com.loan.creditcompany.model.form.AddressForm;
import br.com.loan.creditcompany.repository.AddressRepository;
import br.com.loan.creditcompany.repository.UserRepository;
import br.com.loan.creditcompany.service.AddressService;
import br.com.loan.creditcompany.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserService userService;


    @Override
    public List<AddressDTO> getAllAddress() {
        List<AddressEntity> addressEntities = Optional.ofNullable(addressRepository.findAll()).orElseGet(() -> new ArrayList<>());
        return converterListOfAddressEntityToListOfAddressDTO(addressEntities);
    }

    @Override
    public AddressDTO getOneAddress(Long id) {
        Optional<AddressEntity> addressById = addressRepository.findById(id);
        if (addressById.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Id not found!!");
        }

        return converterAddressEntityToAddressDTO(addressById.get());
    }

    @Override
    public AddressDTO getAddressByUserId(Long userId) {
        return userService.getAddress(userId);
    }

    @Override
    public AddressDTO saveAddress(AddressForm addressForm) {
        AddressEntity addressEntity = converterAddressFormToAddressEntity(addressForm);

        AddressEntity addressSaved = addressRepository.save(addressEntity);
        return converterAddressEntityToAddressDTO(addressSaved);
    }

    @Override
    public AddressDTO updateAddress(Long id, AddressForm addressForm) {
        Optional<AddressEntity> addressById = addressRepository.findById(id);
        if (addressById.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Id not found!!");
        }
        AddressEntity addressEntity = addressById.get();
        addressEntity.setId(addressEntity.getId());
        addressEntity.setStreet(addressForm.getStreet());
        addressEntity.setNumber(addressForm.getNumber());
        addressEntity.setComplements(addressForm.getComplements());
        addressEntity.setZipCode(addressForm.getZipCode());
        addressEntity.setCity(addressForm.getCity());
        addressEntity.setState(addressForm.getState());
        addressEntity.setCountry(addressForm.getCountry());

        AddressEntity addressUpdated = addressRepository.save(addressEntity);
        return converterAddressEntityToAddressDTO(addressUpdated);
    }

    @Override
    public void deleteAddress(Long id) {
        Optional<AddressEntity> addressById = addressRepository.findById(id);
        if (addressById.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Id not found!!") ;
        }
        addressRepository.deleteById(id);
    }

    private List<AddressDTO> converterListOfAddressEntityToListOfAddressDTO(List<AddressEntity> addressEntities) {

        return addressEntities.stream()
                .map(addressEntity -> {
                    AddressDTO addressDTO = new AddressDTO();
                    addressDTO.setId(addressEntity.getId());
                    addressDTO.setStreet(addressEntity.getStreet());
                    addressDTO.setNumber(addressEntity.getNumber());
                    addressDTO.setComplements(addressEntity.getComplements());
                    addressDTO.setZipCode(addressEntity.getZipCode());
                    addressDTO.setCity(addressEntity.getCity());
                    addressDTO.setState(addressEntity.getState());
                    addressDTO.setCountry(addressEntity.getCountry());
                    return addressDTO;
                }).collect(Collectors.toList());
    }

    private AddressDTO converterAddressEntityToAddressDTO(AddressEntity addressEntity) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(addressEntity.getId());
        addressDTO.setStreet(addressEntity.getStreet());
        addressDTO.setNumber(addressEntity.getNumber());
        addressDTO.setComplements(addressEntity.getComplements());
        addressDTO.setZipCode(addressEntity.getZipCode());
        addressDTO.setCity(addressEntity.getCity());
        addressDTO.setState(addressEntity.getState());
        addressDTO.setCountry(addressEntity.getCountry());
        return addressDTO;
    }

    private AddressEntity converterAddressFormToAddressEntity(AddressForm addressForm) {
        AddressEntity addressEntity = new AddressEntity();

        addressEntity.setStreet(addressForm.getStreet());
        addressEntity.setNumber(addressForm.getNumber());
        addressEntity.setComplements(addressForm.getComplements());
        addressEntity.setZipCode(addressForm.getZipCode());
        addressEntity.setCity(addressForm.getCity());
        addressEntity.setState(addressForm.getState());
        addressEntity.setCountry(addressForm.getCountry());

        return addressEntity;
    }
}
