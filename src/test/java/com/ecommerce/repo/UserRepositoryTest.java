package com.ecommerce.repo;

import com.ecommerce.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.mongodb.test.autoconfigure.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    void clean() {
        mongoTemplate.getDb().drop();
    }
    @Test
    void saveAndFindByEmail() {
        User user = new User();
        user.setEmail("test@gmail.com");

        userRepository.save(user);

        Optional<User> found = userRepository.findByEmail("test@gmail.com");

        assertTrue(found.isPresent());
    }

    @Test
    void existsByEmail_ShouldReturnTrue() {
        User user = new User();
        user.setEmail("exists@gmail.com");

        userRepository.save(user);

        assertTrue(userRepository.existsByEmail("exists@gmail.com"));
    }
}