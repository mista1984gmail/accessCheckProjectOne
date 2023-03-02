package org.example.clients.accesscheck;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("access-check")
public interface AccessCheckClient {

    @GetMapping(path = "api/v1/access_check/{customerId}")
    AccessCheckResponse isAccessCheck(@PathVariable("customerId") Integer customerID);
}
