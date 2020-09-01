package br.com.loan.creditcompany.controller;

import br.com.loan.creditcompany.model.DTO.LoanProvidedDTO;
import br.com.loan.creditcompany.service.LoanProvidedService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "Loan")
@RequestMapping("/v0/loan")
public class LoanController {

    @Autowired
    private LoanProvidedService loanProvidedService;

    @GetMapping
    public ResponseEntity<List<LoanProvidedDTO>> getAllLoans(){
        List<LoanProvidedDTO> allLoanProvided = loanProvidedService.getAllLoanProvided();
        return ResponseEntity.ok(allLoanProvided);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanProvidedDTO> getOneLoan(@PathVariable Long id){
        LoanProvidedDTO oneLoanProvided = loanProvidedService.getOneLoanProvided(id);
        return ResponseEntity.ok(oneLoanProvided);
    }

}
