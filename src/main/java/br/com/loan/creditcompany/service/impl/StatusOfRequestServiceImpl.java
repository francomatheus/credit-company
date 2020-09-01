package br.com.loan.creditcompany.service.impl;

import br.com.loan.creditcompany.model.DTO.RequestCreditDTO;
import br.com.loan.creditcompany.model.DTO.StatusOfRequestDTO;
import br.com.loan.creditcompany.service.RequestCreditService;
import br.com.loan.creditcompany.service.StatusOfRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatusOfRequestServiceImpl implements StatusOfRequestService {

    @Autowired
    private RequestCreditService requestCreditService;

    @Override
    public List<StatusOfRequestDTO> getAllStatusOfRequest() {
        List<RequestCreditDTO> allRequestCredit = requestCreditService.getAllRequestCredit();
        return converterListOfRequestCreditToListOfStatus(allRequestCredit);
    }

    @Override
    public StatusOfRequestDTO getStatusOfRequestById(Long id, Long userId) {
        RequestCreditDTO requestCreditByIdAndUserId = requestCreditService.getRequestCreditByIdAndUserId(id, userId);
        return converterRequestCredittoStatus(requestCreditByIdAndUserId);
    }

    @Override
    public List<StatusOfRequestDTO> getAllStatusOfRequestByUserId(Long userId) {
        List<RequestCreditDTO> allRequestsCreditOfUserId = requestCreditService.getAllRequestsCreditOfUserId(userId);
        return converterListOfRequestCreditToListOfStatus(allRequestsCreditOfUserId);
    }

    private StatusOfRequestDTO converterRequestCredittoStatus(RequestCreditDTO requestCreditByIdAndUserId) {
        StatusOfRequestDTO statusOfRequestDTO = new StatusOfRequestDTO();
        statusOfRequestDTO.setId(requestCreditByIdAndUserId.getId());
        statusOfRequestDTO.setDateOfAvailable(requestCreditByIdAndUserId.getDateOfAvailable());
        statusOfRequestDTO.setDateOfRequest(requestCreditByIdAndUserId.getDateOfRequest());
        statusOfRequestDTO.setStatus(requestCreditByIdAndUserId.getStatus());
        statusOfRequestDTO.setError(requestCreditByIdAndUserId.getError());
        return statusOfRequestDTO;
    }

    private List<StatusOfRequestDTO> converterListOfRequestCreditToListOfStatus(List<RequestCreditDTO> allRequestCredit) {
        return allRequestCredit.stream()
                .map(requestCreditDTO -> {
                    StatusOfRequestDTO statusOfRequestDTO = new StatusOfRequestDTO();
                    statusOfRequestDTO.setId(requestCreditDTO.getId());
                    statusOfRequestDTO.setDateOfAvailable(requestCreditDTO.getDateOfAvailable());
                    statusOfRequestDTO.setDateOfRequest(requestCreditDTO.getDateOfRequest());
                    statusOfRequestDTO.setStatus(requestCreditDTO.getStatus());
                    statusOfRequestDTO.setError(requestCreditDTO.getError());
                    return statusOfRequestDTO;
                }).collect(Collectors.toList());

    }
}
