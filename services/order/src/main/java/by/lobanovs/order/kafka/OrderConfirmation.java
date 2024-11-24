package by.lobanovs.order.kafka;

import by.lobanovs.order.dto.customer.CustomerResponse;
import by.lobanovs.order.dto.product.PurchaseResponse;
import by.lobanovs.order.entity.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(

        String orderReference,

        BigDecimal totalAmount,

        PaymentMethod paymentMethod,

        CustomerResponse customer,

        List<PurchaseResponse> products
) {
}
