package by.lobanovs.order.services;

import by.lobanovs.order.dto.order.OrderLineRequest;
import by.lobanovs.order.dto.order.OrderLineResponse;
import by.lobanovs.order.repositories.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository repository;
    private final OrderLineMapper mapper;

    // Сохранение строки заказа
    public Integer saveOrderLine(OrderLineRequest request) {
        var order = mapper.toOrderLine(request);

        return repository.save(order).getId();

    }

    // Получение строк заказа по идентификатору заказа
    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return repository.findAllByOrderId(orderId).stream()
                .map(mapper::toOrderLineResponse)
                .collect(Collectors.toList());
    }
}
