package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    public void addUser(){
        when(userRepository.getAllUsers()).thenReturn(List.of());
        when(userRepository.addUser(any(User.class))).thenReturn(null);
        userService.addUser("login1", "password1");
        verify(userRepository).addUser(any(User.class));
    }

    @Test
    public void getNullLogins(){
        assertThat(userService.getAllLogin(userRepository)).isEqualTo(new ArrayList<>());
    }


    @Test
    public void getAllLogins(){
        when(userRepository.getAllUsers())
                .thenReturn(List.of(new User("login1", "password1"),
                        new User("login2", "password2")));
        List<String> expected = new ArrayList<>() {{
            add("login1");
            add("login2");
        }};
        assertThat(userService.getAllLogin(userRepository)).isEqualTo(expected);
    }



}
