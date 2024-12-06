package by.lobanovs.product.controller;

import by.lobanovs.product.dto.ProductPurchaseRequest;
import by.lobanovs.product.dto.ProductPurchaseResponse;
import by.lobanovs.product.dto.ProductRequest;
import by.lobanovs.product.dto.ProductResponse;
import by.lobanovs.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * REST-контроллер для управления продуктами.
 * Обрабатывает запросы, связанные с созданием, покупкой и получением информации о продуктах.
 */
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {


    private final ProductService service;

    /**
     * Создает новый продукт.
     * @param request объект запроса, содержащий данные о продукте
     * @return идентификатор созданного продукта
     */
    @PostMapping
    public ResponseEntity<Integer> createProduct(
            @RequestBody @Valid ProductRequest request
    ) {
        return ResponseEntity.ok(service.createProduct(request));
    }

    /**
     * Осуществляет покупку одного или нескольких продуктов.
     * @param request список запросов на покупку, содержащих идентификатор продукта и количество
     * @return список ответов с информацией о купленных продуктах
     */
    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> createPurchase(
            @RequestBody List<ProductPurchaseRequest> request
    ) {
        return ResponseEntity.ok(service.purchaseProducts(request));
    }

    /**
     * Возвращает информацию о продукте по его идентификатору.
     * @param productId идентификатор продукта
     * @return объект ответа, содержащий информацию о продукте
     */
    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable("product-id") Integer productId) {
        return ResponseEntity.ok(service.findById(productId));
    }

    /**
     * Возвращает список всех продуктов.
     * @return список объектов ответа с информацией о продуктах
     */
    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}


