package net.engineeringdigest.journalApp.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import net.engineeringdigest.journalApp.entity.User;
import org.springframework.security.test.context.support.WithMockUser;


import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserControllerTests {

    @Autowired
    private UserController userController;

    @Disabled
    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    @WithMockUser(username = "mahi", roles = {"USER"})
    public void testsUpdateUser(User user){
        assertNotNull(userController.updateUser(user));
    }

}
