package by.lobanovs.payment.dto.payment;

import by.lobanovs.order.entity.PaymentMethod;
import by.lobanovs.payment.dto.customer.Customer;

import java.math.BigDecimal;

public record PaymentRequest(


        Integer id,

        BigDecimal amount,

        PaymentMethod paymentMethod,

        Integer orderId,

        String orderReference,

        Customer customer
) {
}
