package br.com.loan.creditcompany.service.impl;

import br.com.loan.creditcompany.model.DTO.LoanProvidedDTO;
import br.com.loan.creditcompany.model.entity.LoanProvidedEntity;
import br.com.loan.creditcompany.repository.LoanProvidedRepository;
import br.com.loan.creditcompany.service.LoanProvidedService;
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
public class LoanProvidedServiceImpl implements LoanProvidedService {

    @Autowired
    private LoanProvidedRepository loanProvidedRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<LoanProvidedDTO> getAllLoanProvided() {
        List<LoanProvidedEntity> loanProvidedEntities = Optional.ofNullable(loanProvidedRepository.findAll()).orElseGet(() -> new ArrayList<>());

        if (loanProvidedEntities.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Not found id!!");
        }

        return converterListOfLoanProvidedEntityToListOfLoanProvidedDTO(loanProvidedEntities);

    }

    @Override
    public LoanProvidedDTO getOneLoanProvided(Long id) {
        Optional<LoanProvidedEntity> loanProvided = loanProvidedRepository.findById(id);
        if (loanProvided.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Not found id!!");
        }

        LoanProvidedEntity loanProvidedEntity = loanProvided.get();

        return converterLoanProvidedEntityToLoanProvidedDTO(loanProvidedEntity);

    }

    @Override
    public void saveLoanProvided(LoanProvidedEntity loanProvidedEntity) {
        loanProvidedRepository.save(loanProvidedEntity);
    }

    private List<LoanProvidedDTO> converterListOfLoanProvidedEntityToListOfLoanProvidedDTO(List<LoanProvidedEntity> loanProvidedEntities) {
        return loanProvidedEntities.stream()
                .map(loanProvidedEntity -> {
                    LoanProvidedDTO loanProvidedDTO = new LoanProvidedDTO();
                    loanProvidedDTO.setDayOfLoanProvided(loanProvidedEntity.getDayOfLoanProvided());
                    loanProvidedDTO.setId(loanProvidedEntity.getId());
                    loanProvidedDTO.setPrice(loanProvidedEntity.getPrice());
                    loanProvidedDTO.setUserDTO(userService.converterUserEntityToUserDTO(loanProvidedEntity.getUserEntity()));

                    return loanProvidedDTO;
                }).collect(Collectors.toList());
    }

    private LoanProvidedDTO converterLoanProvidedEntityToLoanProvidedDTO(LoanProvidedEntity loanProvidedEntity) {
        LoanProvidedDTO loanProvidedDTO = new LoanProvidedDTO();
        loanProvidedDTO.setDayOfLoanProvided(loanProvidedEntity.getDayOfLoanProvided());
        loanProvidedDTO.setId(loanProvidedEntity.getId());
        loanProvidedDTO.setPrice(loanProvidedEntity.getPrice());
        loanProvidedDTO.setUserDTO(userService.converterUserEntityToUserDTO(loanProvidedEntity.getUserEntity()));

        return loanProvidedDTO;
    }
}
