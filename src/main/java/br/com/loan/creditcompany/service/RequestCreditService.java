package br.com.loan.creditcompany.service;

import br.com.loan.creditcompany.model.DTO.RequestCreditDTO;
import br.com.loan.creditcompany.model.form.RequestCreditForm;

import java.util.List;

public interface RequestCreditService {

    List<RequestCreditDTO> getAllRequestCredit();

    RequestCreditDTO getOneRequestCredit(Long id);

    List<RequestCreditDTO> getAllRequestsCreditOfUserId(Long userId);

    RequestCreditDTO getRequestCreditByIdAndUserId(Long id, Long userId);

    void createNewRequestCredit(Long userId,RequestCreditForm requestCreditForm) throws InterruptedException;

    RequestCreditDTO updateRequestCredit(Long id, RequestCreditForm requestCreditForm);

    void deleteRequestCredit(Long id);

}
