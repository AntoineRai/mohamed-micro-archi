package org.example.accountservice.client;

import org.example.accountservice.dto.CardDTO;
import org.example.accountservice.dto.LoanDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClient {

    @Autowired
    private RestTemplate restTemplate;

    private String basicUrl = "http://localhost:8090/";

    public CardDTO getCard(Long accountId) {
        return restTemplate.getForObject(basicUrl + "cards/" + accountId, CardDTO.class);
    }

    public LoanDTO getLoan(Long accountId) {
        return restTemplate.getForObject(basicUrl + "loans/" + accountId, LoanDTO.class);
    }

}
