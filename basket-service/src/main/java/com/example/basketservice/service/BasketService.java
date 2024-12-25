package com.example.basketservice.service;

import com.example.basketservice.model.BasketItem;
import com.example.basketservice.model.ShoppingCart;
import com.example.basketservice.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketService {
    private final BasketRepository basketRepository;

    public ShoppingCart getBasket(String userName) {
        ShoppingCart cart = basketRepository.getBasket(userName);
        return cart != null ? cart : new ShoppingCart();
    }

    public ShoppingCart updateBasket(ShoppingCart basket) {
        basketRepository.updateBasket(basket);
        return basket;
    }

    public void deleteBasket(String userName) {
        basketRepository.deleteBasket(userName);
    }

    public ShoppingCart addItem(String userName, BasketItem item) {
        ShoppingCart cart = getBasket(userName);
        if (cart.getItems() == null) {
            cart.setItems(new ArrayList<>());
        }
        cart.getItems().add(item);
        cart.setUserName(userName);
        return updateBasket(cart);
    }

}
