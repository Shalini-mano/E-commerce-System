package com.ecommerce.repo;

import com.ecommerce.model.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.mongodb.test.autoconfigure.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class CartRepositoryTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    void clean() {
        mongoTemplate.getDb().drop();
    }
    @Autowired
    private CartRepository cartRepository;

    @Test
    void saveAndFindByUserId() {
        Cart cart = new Cart();
        cart.setUserId("user1");

        cartRepository.save(cart);

        Optional<Cart> result = cartRepository.findByUserId("user1");

        assertTrue(result.isPresent());
    }
}