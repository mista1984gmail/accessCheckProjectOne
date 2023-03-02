package org.example.customer.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.customer.model.Customer;
import org.example.customer.service.CustomerService;
import org.example.customer.web.CustomerRegistrationRequest;
import org.example.customer.web.response.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest){
        log.info("New customer registration {}", customerRegistrationRequest);
        customerService.registerCustomer(customerRegistrationRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer findById(
            @PathVariable Integer id) {
        log.info("Find customer with id: " + id);
        return customerService.findById(id);
    }


}
