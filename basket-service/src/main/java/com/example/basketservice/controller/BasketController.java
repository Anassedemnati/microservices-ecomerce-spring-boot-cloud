package com.example.basketservice.controller;

import com.example.basketservice.model.BasketItem;
import com.example.basketservice.model.ShoppingCart;
import com.example.basketservice.requests.BasketCheckoutRequest;
import com.example.basketservice.service.BasketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/basket")
@RequiredArgsConstructor
@Slf4j
public class BasketController {
    private final BasketService basketService;
    private final KafkaTemplate<String, BasketCheckoutRequest> kafkaTemplate;

    @GetMapping("/{userName}")
    public ShoppingCart getBasket(@PathVariable String userName) {
        return basketService.getBasket(userName);
    }

    @PostMapping("/{userName}/items")
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingCart addItem(@PathVariable String userName, @RequestBody BasketItem item) {
        return basketService.addItem(userName, item);
    }

    @PutMapping("/{userName}")
    public ShoppingCart updateBasket(@PathVariable String userName, @RequestBody ShoppingCart basket) {
        basket.setUserName(userName);
        return basketService.updateBasket(basket);
    }

    @DeleteMapping("/{userName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBasket(@PathVariable String userName) {
        basketService.deleteBasket(userName);
    }

    @PostMapping("/checkout")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void checkout(@RequestBody BasketCheckoutRequest basketCheckout) {
        try {
        // get existing basket with total price
        ShoppingCart basket = basketService.getBasket((String) basketCheckout.getUserName());
        if (basket == null) {
            throw new IllegalArgumentException();
        }

        basketCheckout.setTotalPrice(basket.getItems()
                .stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity()).sum());

        String topicName = "checkout-events";

        kafkaTemplate.send(topicName, basketCheckout.getUserName(), basketCheckout)
                .thenAccept(result -> log.info("Event sent successfully for user: {}", basketCheckout.getUserName()))
                .exceptionally(ex -> {
                    log.error("Failed to send event for user: {}", basketCheckout.getUserName(), ex);
                    throw new IllegalArgumentException("Failed to send event for user: "  + basketCheckout.getUserName() + " " + ex.getMessage());
                });

        // remove the basket
        basketService.deleteBasket(basket.getUserName());
        } catch (Exception e) {
            log.error("Failed to process checkout for user: {}", basketCheckout.getUserName(), e);
            throw new IllegalArgumentException();
        }

    }
}
