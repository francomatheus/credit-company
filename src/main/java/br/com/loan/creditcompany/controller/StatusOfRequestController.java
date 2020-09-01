package br.com.loan.creditcompany.controller;

import br.com.loan.creditcompany.model.DTO.StatusOfRequestDTO;
import br.com.loan.creditcompany.service.StatusOfRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v0/status")
public class StatusOfRequestController {

    @Autowired
    private StatusOfRequestService statusOfRequestService;

    @GetMapping
    public ResponseEntity<List<StatusOfRequestDTO>> getAllStatusOfRequest(){
        List<StatusOfRequestDTO> allStatusOfRequest = statusOfRequestService.getAllStatusOfRequest();
        return ResponseEntity.ok(allStatusOfRequest);

    }

    @GetMapping("/request/{id}/user/{userId}")
    public ResponseEntity<StatusOfRequestDTO> getStatusOfRequest(@PathVariable Long id, @PathVariable Long userId){
        StatusOfRequestDTO statusOfRequestById = statusOfRequestService.getStatusOfRequestById(id, userId);
        return ResponseEntity.ok(statusOfRequestById);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<StatusOfRequestDTO>> getAllStatusOfRequestByUserId(@PathVariable Long id){
        List<StatusOfRequestDTO> allStatusOfRequestByUserId = statusOfRequestService.getAllStatusOfRequestByUserId(id);
        return ResponseEntity.ok(allStatusOfRequestByUserId);
    }
}
