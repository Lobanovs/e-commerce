package by.lobanovs.order.product;

import by.lobanovs.order.dto.product.PurchaseRequest;
import by.lobanovs.order.dto.product.PurchaseResponse;
import by.lobanovs.order.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Сервис для взаимодействия с микросервисом продуктов через REST API.
 */
@Service
@RequiredArgsConstructor
public class ProductClient {

    @Value("${application.config.product-url}")
    private String productUrl; // URL микросервиса продуктов

    private final RestTemplate restTemplate;

    /**
     * Осуществляет покупку одного или нескольких продуктов.
     * @param requestBody список запросов на покупку продуктов
     * @return список ответов с данными о купленных продуктах
     */
    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requestBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON_VALUE); // Устанавливаем заголовок Content-Type

        // Формируем HTTP-запрос с телом и заголовками
        HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(requestBody, headers);

        // Тип ответа от микросервиса продуктов
        ParameterizedTypeReference<List<PurchaseResponse>> responseType = new ParameterizedTypeReference<>() {
        };

        // Отправляем POST-запрос в микросервис продуктов
        ResponseEntity<List<PurchaseResponse>> responseEntity = restTemplate.exchange(
                productUrl + "/purchase", // URL для запроса
                POST,
                requestEntity,
                responseType
        );

        // Проверяем наличие ошибок в ответе
        if (responseEntity.getStatusCode().isError()) {
            throw new BusinessException("An error occurred while processing the products purchase: " + responseEntity.getStatusCode());
        }

        // Возвращаем тело ответа с данными о купленных продуктах
        return responseEntity.getBody();
    }
}

