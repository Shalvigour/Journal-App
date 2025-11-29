package net.engineeringdigest.journalApp.Repository;

import net.engineeringdigest.journalApp.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserRepositoryImplTests {

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    @Test
    public void testGetUserForSA(){
        assertNotNull(userRepositoryImpl.getUserForSA());

    }
}
