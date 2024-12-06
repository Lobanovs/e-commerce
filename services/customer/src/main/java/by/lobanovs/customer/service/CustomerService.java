package by.lobanovs.customer.service;

import by.lobanovs.customer.dto.CustomerRequest;
import by.lobanovs.customer.dto.CustomerResponse;
import by.lobanovs.customer.entity.Customer;
import by.lobanovs.customer.exception.CustomerNotFoundException;
import by.lobanovs.customer.repository.CustomerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.*;

@Service
@RequiredArgsConstructor
// Сервисный слой для управления клиентами
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    // Создает нового клиента на основе запроса CustomerRequest
    public String createCustomer(@Valid CustomerRequest request) {

        var customer = repository.save(mapper.toCustomer(request));

        return "Customer created: " + customer.getId();
    }

    // Обновляет данные клиента
    public void updateCustomer(@Valid CustomerRequest request) {

        var customer = repository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Cannot update customer:: No such customer: %s", request.id()))
                );
        // Обновляем поля клиента на основе данных из запроса
        mergerCustomer(customer, request);
        repository.save(customer);
    }

    private void mergerCustomer(Customer customer, @Valid CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstName())){
            customer.setFirstName(request.firstName());
        }
        if(StringUtils.isNotBlank(request.lastName())){
            customer.setLastName(request.lastName());
        }
        if(StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if(request.address() != null){
            customer.setAddress(request.address());
        }
    }

    // Возвращает список всех клиентов
    public List<CustomerResponse> findAllCustomers(){
        return repository.findAll().stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());
    }

    // Проверяет, существует ли клиент по ID
    public Boolean existById(String customerId) {
        return repository.findById(customerId).isPresent();
    }

    // Находит клиента по ID и возвращает DTO CustomerResponse
    public CustomerResponse findById(String customerId) {
        return repository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(format("No customer found with the provided id:: %s" ,customerId)));
    }

    // Удаляет клиента по ID
    public void deleteCustomer(String customerId) {
        repository.deleteById(customerId);
    }
}
