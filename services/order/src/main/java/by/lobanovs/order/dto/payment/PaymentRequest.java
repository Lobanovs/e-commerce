package by.lobanovs.order.dto.payment;

import by.lobanovs.order.dto.customer.CustomerResponse;
import by.lobanovs.order.entity.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(


        BigDecimal amount,

        PaymentMethod paymentMethod,

        Integer orderId,

        String orderReference,

        CustomerResponse customer
) {
}
