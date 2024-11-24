package by.lobanovs.notification.dto.payment;
import by.lobanovs.notification.standalone.PaymentMethod;
import java.math.BigDecimal;


public record PaymentConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) {
}
