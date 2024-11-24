package by.lobanovs.notification.dto.order;

import by.lobanovs.notification.standalone.Customer;
import by.lobanovs.notification.standalone.PaymentMethod;
import by.lobanovs.notification.standalone.Product;
import java.math.BigDecimal;
import java.util.List;


public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products

) {
}
