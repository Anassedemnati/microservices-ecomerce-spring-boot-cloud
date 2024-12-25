package com.example.basketservice.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class BasketItem implements Serializable {
    private String productId;
    private String productName;
    private int quantity;
    private double price;
}