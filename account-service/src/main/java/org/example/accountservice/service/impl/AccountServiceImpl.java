package org.example.accountservice.service.impl;

import org.example.accountservice.dto.CardDTO;
import org.example.accountservice.dto.LoanDTO;
import org.example.accountservice.entity.Account;
import org.example.accountservice.repository.AccountRepository;
import org.example.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.accountservice.client.RestClient;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RestClient restClient;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Override
    public Account getAccountDetails(Long id) {
        Account account = getAccountById(id);
        try {
            CardDTO card = restClient.getCard(id);
            account.setCard(card);
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des cartes : " + e.getMessage());
        }

        try {
            LoanDTO loan = restClient.getLoan(id);
            account.setLoan(loan);
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des prêts : " + e.getMessage());
        }

        return account;
    }

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}
