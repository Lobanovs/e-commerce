package by.lobanovs.customer.controller;

import by.lobanovs.customer.dto.CustomerRequest;
import by.lobanovs.customer.dto.CustomerResponse;
import by.lobanovs.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    // Метод для создания нового клиента
    @PostMapping
    public ResponseEntity<String> createCustomer(
        @RequestBody @Valid CustomerRequest request
    ){
        return ResponseEntity.ok(service.createCustomer(request));
    }

    // Метод для обновления существующего клиента
    @PutMapping
    public ResponseEntity<Void> updateCustomer(
            @RequestBody @Valid CustomerRequest request
    ){
        service.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }

    // Метод для получения списка всех клиентов
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll( ){
        return ResponseEntity.ok(service.findAllCustomers());
    }

    // Метод для проверки существования клиента по его ID
    @GetMapping("/exist/{customer-id}")
    public ResponseEntity<Boolean> existById(
            @PathVariable("customer-id") String customerId
    )
    {
        return ResponseEntity.ok(service.existById(customerId));
    }

    // Метод для получения клиента по его ID
    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findById(
            @PathVariable("customer-id") String customerId
    )
    {
        return ResponseEntity.ok(service.findById(customerId));
    }

    // Метод для удаления клиента по его ID
    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("customer-id") String customerId
    )
    {
        service.deleteCustomer(customerId);
        return ResponseEntity.accepted().build();
    }
}
