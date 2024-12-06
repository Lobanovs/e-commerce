package by.lobanovs.payment.service;


import by.lobanovs.payment.dto.payment.PaymentNotificationRequest;
import by.lobanovs.payment.dto.payment.PaymentRequest;
import by.lobanovs.payment.notification.NotificationProducer;
import by.lobanovs.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;



    public Integer createPayment(PaymentRequest request) {

        var payment = repository.save(mapper.toPayment(request));
        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstName(),
                        request.customer().lastName(),
                        request.customer().email()
                )
        );

        return payment.getId();
    }
}