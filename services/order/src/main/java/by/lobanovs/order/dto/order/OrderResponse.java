package by.lobanovs.order.dto.order;

import by.lobanovs.order.entity.PaymentMethod;

import java.math.BigDecimal;

public record OrderResponse(

    Integer id,

    String reference,

    BigDecimal amount,

    PaymentMethod paymentMethod,

    String customerId

)
{
}
