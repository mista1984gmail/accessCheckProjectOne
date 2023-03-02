package org.example.access.check.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.access.check.service.AccessCheckService;
import org.example.clients.accesscheck.AccessCheckResponse;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/access_check")
public class AccessCheckController {

    private final AccessCheckService accessCheckService;

    @GetMapping(path = "{customerId}")
    public AccessCheckResponse isAccessCheck(@PathVariable ("customerId") Integer customerID){
        boolean isAccessCheckCustomer = accessCheckService.isAccessCustomer(customerID);
        log.info("access check request for customer {}", customerID);
        return new AccessCheckResponse(isAccessCheckCustomer);
    }
}
