package com.ecommerce.repo;

import com.ecommerce.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.mongodb.test.autoconfigure.DataMongoTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class ProductRepositoryTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    void clean() {
        mongoTemplate.getDb().drop();
    }
    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveAndFindProduct() {
        Product product = new Product("Laptop", "Gaming laptop", 1200, 5, "Electronics");

        Product saved = productRepository.save(product);

        Optional<Product> found = productRepository.findById(saved.getId());

        assertTrue(found.isPresent());
        assertEquals("Laptop", found.get().getName());
    }

    @Test
    void searchByNameOrCategory_ShouldReturnResults() {
        productRepository.save(new Product("iPhone", "Apple phone", 900, 10, "Mobile"));

        Page<Product> result =
                productRepository.searchByNameOrCategory("iphone", PageRequest.of(0, 10));

        assertFalse(result.isEmpty());
    }

    @Test
    void findByCategory_ShouldReturnProducts() {
        productRepository.save(new Product("TV", "Smart TV", 500, 2, "Electronics"));

        Page<Product> result =
                productRepository.findByCategory("Electronics", PageRequest.of(0, 10));

        assertTrue(result.getTotalElements() > 0);
    }
}