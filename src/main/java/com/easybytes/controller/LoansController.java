package com.easybytes.controller;

import com.easybytes.model.Loans;
import com.easybytes.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoansController {

    @Autowired
    private LoanRepository loanRepository;

    @GetMapping("/myLoans")
    public List<Loans> getLoanDetails(@RequestParam int id){
        List<Loans> loans = this.loanRepository.findByCustomerIdOrderByStartDtDesc(id);
        if(!loans.isEmpty()){
            return loans;
        }else{
            return null;
        }
    }
}
