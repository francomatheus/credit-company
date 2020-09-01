package br.com.loan.creditcompany.service.impl;

import br.com.loan.creditcompany.model.DTO.RequestCreditDTO;
import br.com.loan.creditcompany.model.DTO.ResponseAboutRequestCreditDTO;
import br.com.loan.creditcompany.model.domain.RequestCreditDomain;
import br.com.loan.creditcompany.model.entity.RequestCreditEntity;
import br.com.loan.creditcompany.model.entity.UserEntity;
import br.com.loan.creditcompany.model.enums.ErrorRequestEnum;
import br.com.loan.creditcompany.model.enums.StatusLoanEnum;
import br.com.loan.creditcompany.model.form.RequestCreditForm;
import br.com.loan.creditcompany.repository.RequestCreditRepository;
import br.com.loan.creditcompany.service.RequestCreditService;
import br.com.loan.creditcompany.service.StageOfCreditAnalysisService;
import br.com.loan.creditcompany.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class RequestCreditServiceImpl implements RequestCreditService {

    @Autowired
    private RequestCreditRepository requestCreditRepository;

    @Autowired
    private StageOfCreditAnalysisService stageOfCreditAnalysisService;

    @Autowired
    private UserService userService;

    @Override
    public List<RequestCreditDTO> getAllRequestCredit() {
        List<RequestCreditEntity> allRequestsCredit = Optional.ofNullable(requestCreditRepository.findAll()).orElseGet(() -> new ArrayList<>());
        return converterListOfRequestsCreditEntityToListOfRequestsCreditDTO(allRequestsCredit);
    }

    @Override
    public RequestCreditDTO getOneRequestCredit(Long id) {
        Optional<RequestCreditEntity> requestCreditById = requestCreditRepository.findById(id);
        if (requestCreditById.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Not found id!!");
        }

        return converterRequestCreditEntityToRequestCreditDTO(requestCreditById.get());

    }

    @Override
    public List<RequestCreditDTO> getAllRequestsCreditOfUserId(Long userId) {

        List<RequestCreditEntity> byUserEntityAndId = Optional.ofNullable(requestCreditRepository.findAllByUserEntity_Id(userId)).orElseGet(() -> new ArrayList<>());

        return converterListOfRequestsCreditEntityToListOfRequestsCreditDTO(byUserEntityAndId);

    }

    @Override
    public RequestCreditDTO getRequestCreditByIdAndUserId(Long id, Long userId) {
        Optional<RequestCreditEntity> byIdAndUserEntityAndId = requestCreditRepository.findByIdAndUserEntityId(id, userId);

        if (byIdAndUserEntityAndId.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Not found id or userId");
        }

        RequestCreditEntity requestCreditEntity = byIdAndUserEntityAndId.get();

        return converterRequestCreditEntityToRequestCreditDTO(requestCreditEntity);

    }

    @Override
    @Async
    public ResponseAboutRequestCreditDTO createNewRequestCredit(Long userId , RequestCreditForm requestCreditForm) throws InterruptedException {

        CompletableFuture.runAsync(() -> {
            RequestCreditEntity requestCreditEntity = converterRequestCredityFormToRequestCreditEntity(requestCreditForm);
            RequestCreditEntity requestCreditEntityCompleted = addSomeStuffsOnRequestCredit(requestCreditEntity, userId);
            RequestCreditEntity requestCreditSaved = requestCreditRepository.save(requestCreditEntityCompleted);
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            RequestCreditDomain requestCreditDomain = converterRequestCreditEntityToRequestCreditDomain(requestCreditSaved);
            stageOfCreditAnalysisService.receivedRequestCredit(requestCreditDomain);
        });

        ResponseAboutRequestCreditDTO responseAboutRequestCreditDTO = new ResponseAboutRequestCreditDTO();
        responseAboutRequestCreditDTO.setResponse("Your order is being processed !!!");

        return responseAboutRequestCreditDTO;

    }

    private RequestCreditEntity addSomeStuffsOnRequestCredit(RequestCreditEntity requestCreditEntity, Long userId) {
        LocalDateTime DateOfRequest = generateDate();
        requestCreditEntity.setDateOfRequest(DateOfRequest);
        requestCreditEntity.setStatus(StatusLoanEnum.PROCESSING);
        requestCreditEntity.setError(ErrorRequestEnum.NO_ERROR);

        UserEntity userEntity = userService.getOneUserAndRetornUserEntity(userId);
        requestCreditEntity.setUserEntity(userEntity);

        return requestCreditEntity;
    }

    private RequestCreditDomain converterRequestCreditEntityToRequestCreditDomain(RequestCreditEntity requestCreditSaved) {
        RequestCreditDomain requestCreditDomain = new RequestCreditDomain();

        requestCreditDomain.setDateOfAvailable(requestCreditSaved.getDateOfAvailable());
        requestCreditDomain.setDateOfRequest(requestCreditSaved.getDateOfRequest());
        requestCreditDomain.setId(requestCreditSaved.getId());
        requestCreditDomain.setPrice(requestCreditSaved.getPrice());
        requestCreditDomain.setStatus(requestCreditSaved.getStatus());
        requestCreditDomain.setUserEntity(requestCreditSaved.getUserEntity());

        return requestCreditDomain;
    }

    @Override
    public RequestCreditDTO updateRequestCredit(Long id, RequestCreditForm requestCreditForm) {
        Optional<RequestCreditEntity> requestCreditById = requestCreditRepository.findById(id);
        if (requestCreditById.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Not found id");
        }

        RequestCreditEntity requestCreditEntity = requestCreditById.get();
        requestCreditEntity.setStatus(requestCreditEntity.getStatus());
        requestCreditEntity.setDateOfRequest(requestCreditEntity.getDateOfRequest());
        requestCreditEntity.setUserEntity(requestCreditEntity.getUserEntity());
        requestCreditEntity.setPrice(requestCreditForm.getPrice());
        requestCreditEntity.setDateOfAvailable(requestCreditEntity.getDateOfAvailable());
        requestCreditEntity.setId(requestCreditEntity.getId());

        RequestCreditEntity requestCreditUpdated = requestCreditRepository.save(requestCreditEntity);
        return converterRequestCreditEntityToRequestCreditDTO(requestCreditUpdated);
    }

    @Override
    public void deleteRequestCredit(Long id) {
        Optional<RequestCreditEntity> requestCreditById = requestCreditRepository.findById(id);

        if (requestCreditById.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Not found id!!");

        }

        requestCreditRepository.deleteById(id);

    }

    private RequestCreditDTO converterRequestCreditEntityToRequestCreditDTO(RequestCreditEntity requestCreditEntity) {

        RequestCreditDTO requestCreditDTO= new RequestCreditDTO();

        requestCreditDTO.setId(requestCreditEntity.getId());
        requestCreditDTO.setPrice(requestCreditEntity.getPrice());
        requestCreditDTO.setUserDTO(userService.converterUserEntityToUserDTO(requestCreditEntity.getUserEntity()));
        requestCreditDTO.setDateOfAvailable(requestCreditEntity.getDateOfAvailable());
        requestCreditDTO.setDateOfRequest(requestCreditEntity.getDateOfRequest());
        requestCreditDTO.setStatus(requestCreditEntity.getStatus());


        return requestCreditDTO;
    }

    private List<RequestCreditDTO> converterListOfRequestsCreditEntityToListOfRequestsCreditDTO(List<RequestCreditEntity> allRequestsCredit) {
        return allRequestsCredit.stream()
                .map(requestCreditEntity -> {
                    RequestCreditDTO requestCreditDTO= new RequestCreditDTO();

                    requestCreditDTO.setId(requestCreditEntity.getId());
                    requestCreditDTO.setPrice(requestCreditEntity.getPrice());
                    requestCreditDTO.setUserDTO(userService.converterUserEntityToUserDTO(requestCreditEntity.getUserEntity()));
                    requestCreditDTO.setDateOfAvailable(requestCreditEntity.getDateOfAvailable());
                    requestCreditDTO.setDateOfRequest(requestCreditEntity.getDateOfRequest());
                    requestCreditDTO.setStatus(requestCreditEntity.getStatus());


                    return requestCreditDTO;
                }).collect(Collectors.toList());
    }

    private LocalDateTime generateDate() {

        return LocalDateTime.now().atZone(ZoneOffset.UTC).toLocalDateTime();
    }

    private RequestCreditEntity converterRequestCredityFormToRequestCreditEntity(RequestCreditForm requestCreditForm) {
        RequestCreditEntity requestCreditEntity = new RequestCreditEntity();

        requestCreditEntity.setPrice(requestCreditForm.getPrice());
        requestCreditEntity.setUserEntity(requestCreditForm.getUserEntity());

        return requestCreditEntity;

    }
}
