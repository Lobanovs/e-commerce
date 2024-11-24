package by.lobanovs.order.controller;


import by.lobanovs.order.dto.order.OrderLineResponse;
import by.lobanovs.order.services.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST-контроллер для управления строками заказов.
 * Обрабатывает запросы, связанные с получением информации о строках заказов.
 */
@RestController
@RequestMapping("/api/v1/order-lines")
@RequiredArgsConstructor
public class OrderLineController {

    private final OrderLineService service;

    /**
     * Возвращает список строк заказа по идентификатору заказа.
     * @param orderId идентификатор заказа
     * @return список объектов ответа с информацией о строках заказа
     */
    @GetMapping("/order/{order-id}")
    public ResponseEntity<List<OrderLineResponse>> findByOrderId(
            @PathVariable("order-id") Integer orderId
    ){
        return ResponseEntity.ok(service.findAllByOrderId(orderId));
    }
}

