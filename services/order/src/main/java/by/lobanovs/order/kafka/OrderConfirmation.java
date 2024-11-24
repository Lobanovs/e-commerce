package by.lobanovs.order.kafka;

import by.lobanovs.order.dto.customer.CustomerResponse;
import by.lobanovs.order.dto.product.PurchaseResponse;
import by.lobanovs.order.entity.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

/**
 * Запись для представления данных подтверждения заказа.
 * Используется для передачи данных между сервисами через Kafka.
 *
 * @param orderReference ссылка на заказ
 * @param totalAmount общая сумма заказа
 * @param paymentMethod способ оплаты
 * @param customer данные клиента, связанного с заказом
 * @param products список купленных продуктов
 */
public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}

