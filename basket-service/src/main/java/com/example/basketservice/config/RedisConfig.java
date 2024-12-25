package com.example.basketservice.config;


import com.example.basketservice.model.ShoppingCart;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, ShoppingCart> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, ShoppingCart> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // Use Jackson2JsonRedisSerializer for ShoppingCart
        Jackson2JsonRedisSerializer<ShoppingCart> serializer = new Jackson2JsonRedisSerializer<>(ShoppingCart.class);
        template.setDefaultSerializer(serializer);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);

        return template;
    }
}
