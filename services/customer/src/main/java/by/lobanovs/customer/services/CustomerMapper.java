package by.lobanovs.customer.services;

import by.lobanovs.customer.dto.CustomerRequest;
import by.lobanovs.customer.dto.CustomerResponse;
import by.lobanovs.customer.entity.Customer;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
// Класс для преобразования DTO в сущности и наоборот
public class CustomerMapper {

    // Преобразует CustomerRequest в сущность Customer
    public Customer toCustomer(@Valid CustomerRequest request) {
        if(request == null) return null;

        return Customer.builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .address(request.address())
                .build();
    }

    // Преобразует сущность Customer в DTO CustomerResponse
    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
