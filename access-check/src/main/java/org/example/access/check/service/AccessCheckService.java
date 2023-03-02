package org.example.access.check.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.access.check.model.AccessCheckHistory;
import org.example.access.check.repository.AccessCheckRepository;
import org.example.customer.model.Customer;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccessCheckService {
    private final String CUSTOMER_URL = "http://localhost:8083/api/v1/customers/";
    private final AccessCheckRepository accessCheckRepository;
    private final BanEmailService banEmailService;
    private final RestTemplate restTemplate;

    public boolean isAccessCustomer(Integer customerId){

        Customer customer = restTemplate
                .getForObject(CUSTOMER_URL + customerId, Customer.class);

        log.info("Check customer email: " + customer.getEmail());
        if(!banEmailService.isBanEmail(customer.getEmail())){

        accessCheckRepository.save(
                AccessCheckHistory.builder()
                        .customerId(customerId)
                        .isAccessCheck(true)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
            return false;
        }
        else return true;



    }
}
