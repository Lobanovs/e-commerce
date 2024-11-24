package by.lobanovs.order.client;

import by.lobanovs.order.dto.customer.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

/**
 * Feign-клиент для взаимодействия с микросервисом клиентов.
 */
@FeignClient(name = "customer-service", url = "${application.config.customer-url}")
public interface CustomerClient {

    /**
     * Получает данные клиента по его идентификатору.
     * @param customerId идентификатор клиента
     * @return объект с данными клиента, обернутый в Optional
     */
    @GetMapping("/{customer-id}")
    Optional<CustomerResponse> findCustomerById(@PathVariable("customer-id") String customerId);
}

