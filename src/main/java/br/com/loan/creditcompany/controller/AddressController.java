package br.com.loan.creditcompany.controller;

import br.com.loan.creditcompany.model.DTO.AddressDTO;
import br.com.loan.creditcompany.model.form.AddressForm;
import br.com.loan.creditcompany.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/v0/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAllAddress(){
        List<AddressDTO> allAddress = addressService.getAllAddress();
        return ResponseEntity.ok(allAddress);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long id){
        AddressDTO oneAddress = addressService.getOneAddress(id);
        return ResponseEntity.ok(oneAddress);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<AddressDTO> getAddressByUserId(@PathVariable Long id){
        AddressDTO addressByUserId = addressService.getAddressByUserId(id);
        return ResponseEntity.ok(addressByUserId);
    }

    @PostMapping
    public ResponseEntity<AddressDTO> createNewAddress(@RequestBody AddressForm addressForm) throws URISyntaxException {
        AddressDTO addressDTO = addressService.saveAddress(addressForm);
        return ResponseEntity
                .created(new URI("/v0/address/".concat(addressDTO.getId().toString()))).body(addressDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @RequestBody AddressForm addressForm){
        AddressDTO addressDTO = addressService.updateAddress(id, addressForm);
        return ResponseEntity.accepted().body(addressDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long id){
        addressService.deleteAddress(id);
        return ResponseEntity.ok().build();
    }
}
