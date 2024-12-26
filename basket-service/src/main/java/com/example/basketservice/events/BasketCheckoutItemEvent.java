package com.example.basketservice.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasketCheckoutItemEvent {
    private String productId;
    private String productName;
    private double price;
    private int quantity;
}
