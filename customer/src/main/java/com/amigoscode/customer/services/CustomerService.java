package com.amigoscode.customer.services;

import com.amigoscode.customer.models.CustomerModel;
import com.amigoscode.customer.repository.CustomerRepository;
import com.amigoscode.customer.requests.CustomerRegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository) {
    public void registerCustomer(CustomerRegistrationRequest request) {
        CustomerModel customer = CustomerModel.builder()
                .firstName(request.fistName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        // todo: check if email valid
        // todo: check if email not taken

        customerRepository.save(customer);
    }
}
