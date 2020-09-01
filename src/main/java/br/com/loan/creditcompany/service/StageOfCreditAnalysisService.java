package br.com.loan.creditcompany.service;

import br.com.loan.creditcompany.model.domain.RequestCreditDomain;

public interface StageOfCreditAnalysisService {

    void receivedRequestCredit(RequestCreditDomain requestCreditDomain);

    void profileAnalyse(RequestCreditDomain requestCreditDomain);

    void incomeAnalyse(RequestCreditDomain requestCreditDomain);

    void finishProcess(RequestCreditDomain requestCreditDomain);

}
