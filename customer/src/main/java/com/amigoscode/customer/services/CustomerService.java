package com.amigoscode.customer.services;

import com.amigoscode.customer.models.CustomerModel;
import com.amigoscode.customer.repository.CustomerRepository;
import com.amigoscode.customer.requests.CustomerRegistrationRequest;
import com.amigoscode.customer.responses.FraudCheckResponse;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@AllArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public void registerCustomer(@NotNull CustomerRegistrationRequest request) {
        CustomerModel customer = CustomerModel.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        // todo: check if email valid
        // todo: check if email not taken
        customerRepository.saveAndFlush(customer);


        // FRAUDSTER CHECK
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://localhost:8082/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("This customer is a fraudster");
        }
        // todo: send notification
    }



}
