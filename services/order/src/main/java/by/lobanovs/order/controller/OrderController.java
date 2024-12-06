package by.lobanovs.order.controller;

import by.lobanovs.order.dto.order.OrderRequest;
import by.lobanovs.order.dto.order.OrderResponse;
import by.lobanovs.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST-контроллер для управления заказами.
 * Обрабатывает запросы, связанные с созданием и получением информации о заказах.
 */
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    /**
     * Создает новый заказ.
     * @param request объект запроса, содержащий данные для создания заказа
     * @return идентификатор созданного заказа
     */
    @PostMapping
    public ResponseEntity<Integer> createOrder(
            @RequestBody @Valid OrderRequest request
    ){
        return ResponseEntity.ok(service.createdOrder(request));
    }

    /**
     * Возвращает список всех заказов.
     * @return список объектов ответа с информацией о заказах
     */
    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    /**
     * Возвращает информацию о заказе по его идентификатору.
     * @param orderId идентификатор заказа
     * @return объект ответа, содержащий информацию о заказе
     */
    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse> findById(
            @PathVariable("order-id") Integer orderId
    ){
        return ResponseEntity.ok(service.findById(orderId));
    }
}
