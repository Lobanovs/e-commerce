package by.lobanovs.order.service;

import by.lobanovs.order.dto.order.OrderLineRequest;
import by.lobanovs.order.dto.order.OrderLineResponse;
import by.lobanovs.order.entity.Order;
import by.lobanovs.order.entity.OrderLine;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.id())
                .quantity(request.quantity())
                .order(
                        Order.builder()
                                .id(request.orderId())
                                .build()
                )
                .productId(request.productId())

                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {

        return new OrderLineResponse(orderLine.getId(), orderLine.getQuantity());
    }
}
