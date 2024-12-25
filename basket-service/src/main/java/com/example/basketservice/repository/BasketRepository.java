package com.example.basketservice.repository;

import com.example.basketservice.model.BasketItem;
import com.example.basketservice.model.ShoppingCart;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;



@Repository
@RequiredArgsConstructor
public class BasketRepository {
    private final RedisTemplate<String, ShoppingCart> redisTemplate;

    private static final String BASKET_KEY_PREFIX = "basket:";

    public ShoppingCart getBasket(String userName) {
        return redisTemplate.opsForValue().get(BASKET_KEY_PREFIX + userName);
    }

    public void updateBasket(ShoppingCart basket) {
        redisTemplate.opsForValue().set(BASKET_KEY_PREFIX + basket.getUserName(), basket);
    }

    public void deleteBasket(String userName) {
        redisTemplate.delete(BASKET_KEY_PREFIX + userName);
    }
}