package com.example.basketservice.helpers.mapper;

import com.example.basketservice.events.BasketCheckoutEvent;
import com.example.basketservice.events.BasketCheckoutItemEvent;
import com.example.basketservice.model.BasketItem;
import com.example.basketservice.requests.BasketCheckoutRequest;

import java.util.List;

public class BasketCheckoutMapper {

    public static BasketCheckoutEvent toBasketCheckoutEvent(BasketCheckoutRequest request, List<BasketItem> basketItems) {
        List<BasketCheckoutItemEvent> itemEvents = basketItems.stream()
                .map(item -> BasketCheckoutItemEvent.builder()
                        .productId(item.getProductId())
                        .productName(item.getProductName())
                        .price(item.getPrice())
                        .quantity(item.getQuantity())
                        .build())
                .toList();

        return BasketCheckoutEvent.builder()
                .userName(request.getUserName())
                .totalPrice(request.getTotalPrice())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .emailAddress(request.getEmailAddress())
                .addressLine(request.getAddressLine())
                .country(request.getCountry())
                .state(request.getState())
                .zipCode(request.getZipCode())
                .cardName(request.getCardName())
                .cardNumber(request.getCardNumber())
                .expiration(request.getExpiration())
                .cvv(request.getCvv())
                .paymentMethod(request.getPaymentMethod())
                .items(itemEvents)
                .build();
    }
}

