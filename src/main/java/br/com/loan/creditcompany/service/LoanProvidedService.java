package br.com.loan.creditcompany.service;

import br.com.loan.creditcompany.model.DTO.LoanProvidedDTO;
import br.com.loan.creditcompany.model.entity.LoanProvidedEntity;

import java.util.List;

public interface LoanProvidedService {

    List<LoanProvidedDTO> getAllLoanProvided();

    LoanProvidedDTO getOneLoanProvided(Long id);

    void saveLoanProvided(LoanProvidedEntity loanProvidedEntity);

}
