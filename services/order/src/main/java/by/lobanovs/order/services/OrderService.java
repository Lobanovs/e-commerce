package by.lobanovs.order.services;

import by.lobanovs.order.client.CustomerClient;
import by.lobanovs.order.dto.order.OrderLineRequest;
import by.lobanovs.order.dto.order.OrderRequest;
import by.lobanovs.order.dto.order.OrderResponse;
import by.lobanovs.order.dto.payment.PaymentRequest;
import by.lobanovs.order.exceptions.BusinessException;
import by.lobanovs.order.kafka.OrderConfirmation;
import by.lobanovs.order.kafka.OrderProducer;
import by.lobanovs.order.payment.PaymentClient;
import by.lobanovs.order.product.ProductClient;
import by.lobanovs.order.dto.product.PurchaseRequest;
import by.lobanovs.order.repositories.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

     private final CustomerClient customerClient;
     private final ProductClient productClient;
     private final OrderRepository repository;
     private final OrderMapper mapper;
     private final OrderLineService orderLineService;
     private final OrderProducer orderProducer;
     private final PaymentClient paymentClient;

     public Integer createdOrder(OrderRequest request) {
         // check the customer --> OpenFeign

          var customer = this.customerClient.findCustomerById(request.customerId())
                  .orElseThrow(() -> new BusinessException("Cannot create order:: No Customer exists with the provided ID"));


         // purchase the products --> products ms

          var purchasedProduct = this.productClient.purchaseProducts(request.products());

         // persist order

          var order = this.repository.save(mapper.toOrder(request));

         // persist order lines

          for(PurchaseRequest purchaseRequest: request.products()){
               orderLineService.saveOrderLine(
                       new OrderLineRequest(
                               null,
                               order.getId(),
                               purchaseRequest.productId(),
                               purchaseRequest.quantity()
                       )
               );
          }

         //  start payment process

          var paymentRequest = new PaymentRequest(
                  request.amount(),
                  request.paymentMethod(),
                  order.getId(),
                  order.getReference(),
                  customer
          );

          paymentClient.requestOrderPayment(paymentRequest);


         // send the order confirmation --> notification ms (kafka broker)

          orderProducer.sendOrderConfirmation(
                  new OrderConfirmation(
                          request.reference(),
                          request.amount(),
                          request.paymentMethod(),
                          customer,
                          purchasedProduct
                  )
          );

          return order.getId();
     }

     public List<OrderResponse> findAll() {
          return  repository.findAll()
                  .stream()
                  .map(mapper::fromOrder)
                  .collect(Collectors.toList());
     }

     public OrderResponse findById(Integer orderId) {
          return repository.findById(orderId)
                  .map(mapper::fromOrder)
                  .orElseThrow(() -> new EntityNotFoundException(String.format("Order with ID %d not found", orderId)));
     }
}
