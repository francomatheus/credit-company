package br.com.loan.creditcompany.service;

import br.com.loan.creditcompany.model.DTO.StatusOfRequestDTO;

import java.util.List;

public interface StatusOfRequestService {

    List<StatusOfRequestDTO> getAllStatusOfRequest();

    StatusOfRequestDTO getStatusOfRequestById(Long id, Long userId);

    List<StatusOfRequestDTO> getAllStatusOfRequestByUserId(Long userId);

}
