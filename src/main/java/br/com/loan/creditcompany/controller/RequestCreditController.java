package br.com.loan.creditcompany.controller;

import br.com.loan.creditcompany.model.DTO.RequestCreditDTO;
import br.com.loan.creditcompany.model.form.RequestCreditForm;
import br.com.loan.creditcompany.service.RequestCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v0/request")
public class RequestCreditController {

    @Autowired
    private RequestCreditService requestCreditService;

    @GetMapping
    public ResponseEntity<List<RequestCreditDTO>> getAllRequestCredit(){
        List<RequestCreditDTO> allRequestCredit = requestCreditService.getAllRequestCredit();
        return ResponseEntity.ok(allRequestCredit);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestCreditDTO> getOneRequestCredit(@PathVariable Long id){
        RequestCreditDTO oneRequestCredit = requestCreditService.getOneRequestCredit(id);
        return ResponseEntity.ok(oneRequestCredit);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<RequestCreditDTO>> getRequestCreditByUserID(@PathVariable Long id){
        List<RequestCreditDTO> allRequestsCreditOfUserId = requestCreditService.getAllRequestsCreditOfUserId(id);
        return ResponseEntity.ok(allRequestsCreditOfUserId);

    }

    @GetMapping("/{id}/user/{userId}")
    public ResponseEntity<RequestCreditDTO> getRequestCreditByIdAndUserId(@PathVariable Long id, @PathVariable Long userId){
        RequestCreditDTO requestCreditByIdAndUserId = requestCreditService.getRequestCreditByIdAndUserId(id, userId);
        return ResponseEntity.ok(requestCreditByIdAndUserId);
    }


    @PostMapping("/user/{userId}")
    public ResponseEntity<String> createNewRequestCredit(@PathVariable Long userId,
                                                         @RequestBody RequestCreditForm requestCreditForm) throws InterruptedException {
        requestCreditService.createNewRequestCredit(userId,requestCreditForm);

        return ResponseEntity.ok("Your Order is being processed !!!");

    }

    @PutMapping("/{id}")
    public ResponseEntity<RequestCreditDTO> updateRequestCredit(@PathVariable Long id, @RequestBody RequestCreditForm requestCreditForm){
        RequestCreditDTO requestCreditDTO = requestCreditService.updateRequestCredit(id, requestCreditForm);
        return ResponseEntity.accepted().body(requestCreditDTO);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRequest(@PathVariable Long id){
        requestCreditService.deleteRequestCredit(id);
        return ResponseEntity.ok().build();
    }
}
