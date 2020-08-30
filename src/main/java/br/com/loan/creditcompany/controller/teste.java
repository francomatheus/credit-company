package br.com.loan.creditcompany.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class teste {

    @GetMapping("/teste")
    public String teste(){
        return "Ok.";
    }
}
