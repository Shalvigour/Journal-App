package net.engineeringdigest.journalApp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import net.engineeringdigest.journalApp.repository.UserRepository;
import net.engineeringdigest.journalApp.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserDetailsServiceImplTests {
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Disabled
    @Test
    public void testLoadUserByUsername() {
        User user = User.builder().userName("test").password("hfjsdfkjsghvbxncb").build();
        when(userRepository.findByUserName("test")).thenReturn(Optional.of(user));
        UserDetails userDetails = userDetailsService.loadUserByUsername("test");
        assertEquals("test", userDetails.getUsername());
    }
}
