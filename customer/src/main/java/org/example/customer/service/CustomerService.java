package org.example.customer.service;

import lombok.extern.slf4j.Slf4j;
import org.example.clients.accesscheck.AccessCheckClient;
import org.example.clients.accesscheck.AccessCheckResponse;
import org.example.clients.notification.NotificationRequest;
import org.example.customer.exception.NoAccessException;
import org.example.customer.model.Customer;
import org.example.customer.repository.CustomerRepository;
import org.example.customer.web.CustomerRegistrationRequest;
import org.example.message.queue.RabbitMQMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CustomerService {

    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;
    @Value("${rabbitmq.routing-keys.internal-notification}")
    private String internalNotificationRoutingKey;

    private final CustomerRepository customerRepository;
    private final AccessCheckClient accessCheckClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, AccessCheckClient accessCheckClient, RabbitMQMessageProducer rabbitMQMessageProducer) {
        this.customerRepository = customerRepository;
        this.accessCheckClient = accessCheckClient;
        this.rabbitMQMessageProducer = rabbitMQMessageProducer;
    }

    public Customer findById(Integer id) {
        log.debug("Find customer with id: {}", id);
        return Optional.of(getById(id)).get();
    }

    private Customer getById(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Customer with id: " + id + "not found"));
    }

    public void registerCustomer(CustomerRegistrationRequest request) {

        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .build();
        customerRepository.saveAndFlush(customer);
        log.info("Save customer:  " + customer);

        log.info("Check customer on access:  " + customer);
        AccessCheckResponse accessCheckResponse = accessCheckClient.isAccessCheck(customer.getId());

        if(accessCheckResponse.getIsAccessCheck()){
            throw new NoAccessException("No access");
        }

        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to Service...",
                        customer.getFirstName())
        );

        rabbitMQMessageProducer.publish(
                notificationRequest,
                internalExchange,
                internalNotificationRoutingKey
        );
    }

}
