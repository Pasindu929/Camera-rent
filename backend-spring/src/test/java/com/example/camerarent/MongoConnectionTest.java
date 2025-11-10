package com.example.camerarent;

import com.example.camerarent.user.User;
import com.example.camerarent.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {
    "spring.data.mongodb.uri=mongodb://localhost:27017/camerarent-test"
})
public class MongoConnectionTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testMongoConnection() {
        // This test will fail if MongoDB is not running or not accessible
        assertNotNull(userRepository, "UserRepository should be autowired");
        
        // Test basic CRUD operation
        User testUser = new User();
        testUser.setUsername("testuser");
        testUser.setPassword("testpass123");
        
        // Save user
        User savedUser = userRepository.save(testUser);
        assertNotNull(savedUser.getId(), "User should have an ID after saving");
        
        // Find user
        var foundUser = userRepository.findByUsername("testuser");
        assertTrue(foundUser.isPresent(), "User should be found by username");
        assertEquals("testuser", foundUser.get().getUsername());
        
        // Clean up
        userRepository.deleteById(savedUser.getId());
        
        System.out.println("✅ MongoDB connection test passed!");
    }
}
