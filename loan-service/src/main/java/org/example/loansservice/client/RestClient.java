package org.example.loansservice.client;

import org.example.loansservice.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClient {

    @Autowired
    private RestTemplate restTemplate;

    private String productServiceUrl = "http://localhost:8090/accounts/";

    public AccountDTO getAccount(Long accountId) {
        return restTemplate.getForObject(productServiceUrl + accountId, AccountDTO.class);
    }


}