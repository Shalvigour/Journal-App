package net.engineeringdigest.journalApp.Repository;

import net.engineeringdigest.journalApp.repository.UserSentimentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserRepositoryImplTests {

    @Autowired
    private UserSentimentRepository userRepositoryImpl;

    @Test
    public void testGetUserForSA(){
        assertNotNull(userRepositoryImpl.getUserForSA());

    }
}
