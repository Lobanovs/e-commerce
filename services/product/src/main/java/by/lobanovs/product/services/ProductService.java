package by.lobanovs.product.services;

import by.lobanovs.product.dto.ProductPurchaseRequest;
import by.lobanovs.product.dto.ProductPurchaseResponse;
import by.lobanovs.product.dto.ProductRequest;
import by.lobanovs.product.dto.ProductResponse;
import by.lobanovs.product.exception.ProductPurchaseException;
import by.lobanovs.product.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    // Метод для создания нового продукта
    public Integer createProduct(ProductRequest request) {

        var product = mapper.toProduct(request);
        return repository.save(product).getId();
    }

    // Метод для покупки продуктов
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {

        // Получаем список ID всех запрашиваемых продуктов.
        var productIds = request.stream()
                .map(ProductPurchaseRequest::productId)
                .toList();

        // Ищем продукты в базе данных по указанным ID.
        var storedProduct = repository.findAllByIdInOrderById(productIds);

        // Если количество найденных продуктов не совпадает с количеством запрошенных, выбрасываем исключение
        if (productIds.size() != storedProduct.size()) {
            throw new ProductPurchaseException("One or more product does not exist");
        }

        // Сортируем список запросов по ID продукта, чтобы соответствовать порядку найденных продуктов
        var storedRequest = request.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        // Проверяем доступное количество каждого продукта и обновляем его
        for (int i = 0; i < storedProduct.size(); i++) {
            var product = storedProduct.get(i); // Продукт из базы данных
            var productRequest = storedRequest.get(i); // Соответствующий запрос

            // Если доступного количества недостаточно, выбрасываем исключение
            if(product.getAvailableQuantity() < productRequest.quantity()){
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID:: " + productRequest.productId());
            }

            // Обновляем доступное количество продукта.
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            repository.save(product);  // Сохраняем обновленный продукт

            // Добавляем информацию о покупке в список ответа
            purchasedProducts.add(mapper.toProductPurchaseResponse(product, productRequest.quantity()));

        }

        return purchasedProducts; // Возвращаем список успешно купленных продуктов
    }

    // Метод для получения продукта по ID.
    public ProductResponse findById(Integer productId) {
        return repository.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with the ID:: " + productId));
    }

    // Метод для получения списка всех продуктов.
    public List<ProductResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
