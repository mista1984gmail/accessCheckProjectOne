package org.example.access.check;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AccessCheckApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccessCheckApplication.class, args);
    }
}
