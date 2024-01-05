package com.easybytes.controller;

import com.easybytes.model.AccountTransactions;
import com.easybytes.repository.AccountsTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BalanceController {

    @Autowired
    private AccountsTransactionRepository accountsTransactionRepository;

    @GetMapping("/myBalance")
    public List<AccountTransactions> getBalanceDetails(@RequestParam int id){
        List<AccountTransactions> accountTransactions = this.accountsTransactionRepository.findByCustomerIdOrderByTransactionDtDesc(id);
        if(!accountTransactions.isEmpty()){
            return accountTransactions;
        }else{
            return null;
        }
    }
}
