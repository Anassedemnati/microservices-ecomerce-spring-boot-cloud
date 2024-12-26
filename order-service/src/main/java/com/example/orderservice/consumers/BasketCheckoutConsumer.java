package com.example.orderservice.consumers;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderItem;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BasketCheckoutConsumer {
    private final OrderService orderService;

    @KafkaListener(
            topics = "checkout-events",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(BasketCheckoutEvent basketCheckoutEvent) {
        try {
            if (basketCheckoutEvent == null || basketCheckoutEvent.getItems() == null) {
                log.error("Invalid basket checkout event: {}", basketCheckoutEvent);
                return;
            }

            log.info("Received checkout event for user: {}", basketCheckoutEvent.getUserName());
            log.info("Total Price: {}", basketCheckoutEvent.getTotalPrice());

            Order createdOrder = orderService.createOrder(basketCheckoutEvent);
            log.info("Order created: {}", createdOrder.getId());

            // Commit the offset
            //acknowledgment.acknowledge();
        } catch (Exception e) {
            log.error("Error processing the checkout event", e);
            // Optionally rethrow to handle in error handler
            throw e;
        }
    }
}