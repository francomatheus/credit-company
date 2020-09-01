package br.com.loan.creditcompany.service.impl;

import br.com.loan.creditcompany.model.domain.RequestCreditDomain;
import br.com.loan.creditcompany.model.entity.LoanProvidedEntity;
import br.com.loan.creditcompany.model.entity.RequestCreditEntity;
import br.com.loan.creditcompany.model.enums.ErrorRequestEnum;
import br.com.loan.creditcompany.model.enums.StatusLoanEnum;
import br.com.loan.creditcompany.repository.RequestCreditRepository;
import br.com.loan.creditcompany.service.LoanProvidedService;
import br.com.loan.creditcompany.service.StageOfCreditAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Service
public class StageOfCreditAnalysisServiceImpl implements StageOfCreditAnalysisService {

    @Autowired
    private RequestCreditRepository requestCreditRepository;

    @Autowired
    private LoanProvidedService loanProvidedService;

    @Override
    @Async
    public void receivedRequestCredit(RequestCreditDomain requestCreditDomain) {
        Optional<RequestCreditEntity> requestCreditRepositoryById = requestCreditRepository.findById(requestCreditDomain.getId());
        RequestCreditEntity requestCreditEntity = requestCreditRepositoryById.get();
        if (requestCreditRepositoryById.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Problem with process, contact us!!");
        }
        try {
            requestCreditEntity.setId(requestCreditEntity.getId());
            requestCreditEntity.setDateOfAvailable(requestCreditEntity.getDateOfAvailable());
            requestCreditEntity.setPrice(requestCreditEntity.getPrice());
            requestCreditEntity.setUserEntity(requestCreditEntity.getUserEntity());
            requestCreditEntity.setDateOfRequest(requestCreditEntity.getDateOfRequest());
            requestCreditEntity.setError(requestCreditEntity.getError());
            requestCreditEntity.setStatus(StatusLoanEnum.PROCESSING);

            requestCreditRepository.save(requestCreditEntity);

            timeToProcess();

            profileAnalyse(requestCreditDomain);
        } catch (InterruptedException e) {
            e.printStackTrace();
            requestCreditEntity.setId(requestCreditEntity.getId());
            requestCreditEntity.setDateOfAvailable(requestCreditEntity.getDateOfAvailable());
            requestCreditEntity.setPrice(requestCreditEntity.getPrice());
            requestCreditEntity.setUserEntity(requestCreditEntity.getUserEntity());
            requestCreditEntity.setDateOfRequest(requestCreditEntity.getDateOfRequest());
            requestCreditEntity.setStatus(StatusLoanEnum.PROCESSING);
            requestCreditEntity.setError(ErrorRequestEnum.ERROR_PROCESSING);

            requestCreditRepository.save(requestCreditEntity);
        }
    }



    @Override
    @Async
    public void profileAnalyse(RequestCreditDomain requestCreditDomain) {
        Optional<RequestCreditEntity> requestCreditRepositoryById = requestCreditRepository.findById(requestCreditDomain.getId());
        if (requestCreditRepositoryById.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Problem with process, contact us!!");
        }
        RequestCreditEntity requestCreditEntity = requestCreditRepositoryById.get();
        try {
            requestCreditEntity.setId(requestCreditEntity.getId());
            requestCreditEntity.setDateOfAvailable(requestCreditEntity.getDateOfAvailable());
            requestCreditEntity.setPrice(requestCreditEntity.getPrice());
            requestCreditEntity.setUserEntity(requestCreditEntity.getUserEntity());
            requestCreditEntity.setDateOfRequest(requestCreditEntity.getDateOfRequest());
            requestCreditEntity.setError(requestCreditEntity.getError());
            requestCreditEntity.setStatus(StatusLoanEnum.CHECKING_PROFILE);

            requestCreditRepository.save(requestCreditEntity);
            timeToProcess();

            incomeAnalyse(requestCreditDomain);
        } catch (InterruptedException e) {
            e.printStackTrace();
            requestCreditEntity.setId(requestCreditEntity.getId());
            requestCreditEntity.setDateOfAvailable(requestCreditEntity.getDateOfAvailable());
            requestCreditEntity.setPrice(requestCreditEntity.getPrice());
            requestCreditEntity.setUserEntity(requestCreditEntity.getUserEntity());
            requestCreditEntity.setDateOfRequest(requestCreditEntity.getDateOfRequest());
            requestCreditEntity.setError(ErrorRequestEnum.ERROR_PROFILE);
            requestCreditEntity.setStatus(StatusLoanEnum.CHECKING_PROFILE);
            requestCreditRepository.save(requestCreditEntity);
        }
    }

    @Override
    @Async
    public void incomeAnalyse(RequestCreditDomain requestCreditDomain) {
        Optional<RequestCreditEntity> requestCreditRepositoryById = requestCreditRepository.findById(requestCreditDomain.getId());
        if (requestCreditRepositoryById.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Problem with process, contact us!!");
        }
        RequestCreditEntity requestCreditEntity = requestCreditRepositoryById.get();
        try {
            requestCreditEntity.setId(requestCreditEntity.getId());
            requestCreditEntity.setDateOfAvailable(requestCreditEntity.getDateOfAvailable());
            requestCreditEntity.setPrice(requestCreditEntity.getPrice());
            requestCreditEntity.setUserEntity(requestCreditEntity.getUserEntity());
            requestCreditEntity.setDateOfRequest(requestCreditEntity.getDateOfRequest());
            requestCreditEntity.setError(requestCreditEntity.getError());
            requestCreditEntity.setStatus(StatusLoanEnum.CHECKING_CREDIT);

            requestCreditRepository.save(requestCreditEntity);
            timeToProcess();

            finishProcess(requestCreditDomain);
        } catch (InterruptedException e) {
            e.printStackTrace();
            requestCreditEntity.setId(requestCreditEntity.getId());
            requestCreditEntity.setDateOfAvailable(requestCreditEntity.getDateOfAvailable());
            requestCreditEntity.setPrice(requestCreditEntity.getPrice());
            requestCreditEntity.setUserEntity(requestCreditEntity.getUserEntity());
            requestCreditEntity.setDateOfRequest(requestCreditEntity.getDateOfRequest());
            requestCreditEntity.setError(requestCreditEntity.getError());
            requestCreditEntity.setStatus(StatusLoanEnum.CHECKING_CREDIT);
            requestCreditRepository.save(requestCreditEntity);
        }
    }

    @Override
    @Async
    @Transactional
    public void finishProcess(RequestCreditDomain requestCreditDomain) {
        Optional<RequestCreditEntity> requestCreditRepositoryById = requestCreditRepository.findById(requestCreditDomain.getId());
        if (requestCreditRepositoryById.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Problem with process, contact us!!");
        }
        RequestCreditEntity requestCreditEntity = requestCreditRepositoryById.get();
        LocalDateTime dateOfAvailable = generateDate();
        try {


            requestCreditEntity.setId(requestCreditEntity.getId());
            requestCreditEntity.setDateOfAvailable(requestCreditEntity.getDateOfAvailable());
            requestCreditEntity.setPrice(requestCreditEntity.getPrice());
            requestCreditEntity.setUserEntity(requestCreditEntity.getUserEntity());
            requestCreditEntity.setDateOfRequest(requestCreditEntity.getDateOfRequest());
            requestCreditEntity.setError(requestCreditEntity.getError());
            requestCreditEntity.setStatus(StatusLoanEnum.AVAILABLE);
            requestCreditEntity.setDateOfAvailable(dateOfAvailable);

            RequestCreditEntity save = requestCreditRepository.save(requestCreditEntity);
            timeToProcess();

            LoanProvidedEntity loanProvidedEntity = new LoanProvidedEntity();
            loanProvidedEntity.setDayOfLoanProvided(dateOfAvailable);
            loanProvidedEntity.setPrice(requestCreditEntity.getPrice());
            loanProvidedEntity.setUserEntity(requestCreditEntity.getUserEntity());

            loanProvidedService.saveLoanProvided(loanProvidedEntity);

        } catch (InterruptedException e) {
            e.printStackTrace();
            requestCreditEntity.setId(requestCreditEntity.getId());
            requestCreditEntity.setDateOfAvailable(requestCreditEntity.getDateOfAvailable());
            requestCreditEntity.setPrice(requestCreditEntity.getPrice());
            requestCreditEntity.setUserEntity(requestCreditEntity.getUserEntity());
            requestCreditEntity.setDateOfRequest(requestCreditEntity.getDateOfRequest());
            requestCreditEntity.setError(ErrorRequestEnum.ERROR_FINISH);
            requestCreditEntity.setStatus(StatusLoanEnum.AVAILABLE);
            requestCreditEntity.setDateOfAvailable(dateOfAvailable);

            RequestCreditEntity save = requestCreditRepository.save(requestCreditEntity);
        }
    }

    @Async
    public void timeToProcess() throws InterruptedException {
        Thread.sleep(5000L);
    }

    private LocalDateTime generateDate(){
        return LocalDateTime.now().atZone(ZoneOffset.UTC).toLocalDateTime();
    }
}
