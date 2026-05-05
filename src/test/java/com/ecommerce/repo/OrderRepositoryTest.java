package com.ecommerce.repo;

import com.ecommerce.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.mongodb.test.autoconfigure.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class OrderRepositoryTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    void clean() {
        mongoTemplate.getDb().drop();
    }
    @Autowired
    private OrderRepository orderRepository;

    @Test
    void saveAndFindByUserId() {
        Order order = new Order();
        order.setUserId("user1");

        orderRepository.save(order);

        List<Order> orders = orderRepository.findByUserId("user1");

        assertFalse(orders.isEmpty());
    }

    @Test
    void findByIdempotencyKey_ShouldReturnOrder() {
        Order order = new Order();
        order.setIdempotencyKey("key-123");

        orderRepository.save(order);

        Optional<Order> result =
                orderRepository.findByIdempotencyKey("key-123");

        assertTrue(result.isPresent());
    }
}