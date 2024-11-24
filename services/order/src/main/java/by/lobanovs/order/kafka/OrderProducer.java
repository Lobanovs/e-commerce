package by.lobanovs.order.kafka;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * Сервис для отправки сообщений подтверждения заказа в Kafka.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderProducer {

    private final KafkaTemplate<String, OrderConfirmation> kafkaTemplate;

    /**
     * Отправляет подтверждение заказа в Kafka.
     * @param orderConfirmation объект, содержащий информацию о подтверждении заказа
     */
    public void sendOrderConfirmation(OrderConfirmation orderConfirmation) {
        log.info("Sending order confirmation: {}", orderConfirmation);

        // Создаем сообщение с полезной нагрузкой и заголовком для Kafka
        Message<OrderConfirmation> message = MessageBuilder
                .withPayload(orderConfirmation)
                .setHeader(KafkaHeaders.TOPIC, "order-topic") // Указываем Kafka-топик
                .build();

        // Отправляем сообщение в Kafka
        kafkaTemplate.send(message);
    }
}

