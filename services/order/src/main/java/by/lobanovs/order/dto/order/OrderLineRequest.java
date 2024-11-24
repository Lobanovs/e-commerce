package by.lobanovs.order.dto.order;

public record OrderLineRequest(

        Integer id,

        Integer orderId,

        Integer productId,

        double quantity
) {
}
