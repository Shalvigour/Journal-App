package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.*;//test,disables
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Disabled
    @ArgumentsSource(UserArgumentsProvider.class)
    @ParameterizedTest
    public void testNewSaveUser(User user){
        assertTrue(userService.newSaveEntry(user));
    }

    @Disabled
    @ParameterizedTest
    @ArgumentsSource(AdminArgumentsProvider.class)
    public void testAdminSaveEntry(User user){
        assertTrue(userService.newAdminSaveEntry(user));
    }


    @ParameterizedTest
    @CsvSource({
            "1,2,3",
            "2,3,5",
            "2,2,4"
    })
    public void test(int a,int b,int c){
        assertEquals(c,a+b);
    }


    public static Stream<String> testing(){
        return Stream.of( "shalvi","mahima"
        );
    }


    @Disabled
    @ParameterizedTest
    @MethodSource("testing")
    public void testsTesting(String a){
        assertTrue(a.equals("mahima"));
    }
}