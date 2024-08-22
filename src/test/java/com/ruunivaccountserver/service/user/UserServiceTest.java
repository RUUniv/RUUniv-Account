package com.ruunivaccountserver.service.user;

import static org.mockito.BDDMockito.*;

import com.ruunivaccountserver.app.user.entity.User;
import com.ruunivaccountserver.app.user.repository.UserRepository;
import com.ruunivaccountserver.app.user.service.UserService;
import com.ruunivaccountserver.common.error.user.MaxApiKeyException;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    @DisplayName("Check Api Key Count Max Success (Api Key Count < 3) Test")
    void checkApiKeyCountMaxSuccessTest(){
        User user = User.builder()
                .id(1L)
                .email("test@test.com")
                .apiKeyCount(0)
                .build();

        given(userRepository.findById(1L)).willReturn(Optional.of(user));

        userService.checkApiKeyCountMax(1L);

        verify(userRepository).save(user);
    }

    @Test
    @DisplayName("Check Api Key Count Max Failure (Api Key Count >= 3) Test")
    void checkApiKeyCountMaxFailureTest(){
        User user = User.builder()
                .id(1L)
                .email("test@test.com")
                .apiKeyCount(4)
                .build();

        given(userRepository.findById(1L)).willReturn(Optional.of(user));

        Assertions.assertThrows(MaxApiKeyException.class,()->userService.checkApiKeyCountMax(1L));
    }
}
