package com.ruunivaccountserver.service.auth;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import com.ruunivaccountserver.app.auth.dto.AuthRequest.LoginRequest;
import com.ruunivaccountserver.app.auth.dto.AuthRequest.SignUpRequest;
import com.ruunivaccountserver.app.auth.dto.AuthResponse.TokenResponse;
import com.ruunivaccountserver.app.auth.service.AuthService;
import com.ruunivaccountserver.app.auth.service.EncryptUtil;
import com.ruunivaccountserver.app.auth.service.JwtService;
import com.ruunivaccountserver.app.user.entity.User;
import com.ruunivaccountserver.app.user.repository.UserRepository;
import com.ruunivaccountserver.common.error.user.UserNotFoundException;
import java.util.Optional;
import org.apache.kafka.common.compress.KafkaLZ4BlockOutputStream.BD;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @InjectMocks
    AuthService authService;

    @Mock
    JwtService jwtService;

    @Mock
    UserRepository userRepository;

    @Mock
    EncryptUtil encryptUtil;

    String email = "test@test.com";
    String password = "test";

    TokenResponse tokenResponse = new TokenResponse("access","refresh");

    @Test
    @DisplayName("SignUp Success Test")
    void signUpSuccessTest(){
        SignUpRequest request = new SignUpRequest(email, password);

        User saveUser = User.builder()
                .id(1L)
                .email(email)
                .password(password)
                .build();


        given(userRepository.save(any(User.class))).willReturn(saveUser);
        given(jwtService.generateToken(1L)).willReturn(tokenResponse);

        TokenResponse response = authService.signUp(request);

        assertEquals(tokenResponse,response);
    }

    @Test
    @DisplayName("SignUp Failure By Duplicate Email Test")
    void signUpFailureByDuplicateEmailTest(){
        SignUpRequest request = new SignUpRequest(email, password);

        User saveUser = User.builder()
                .id(1L)
                .email(email)
                .password(password)
                .build();

        given(userRepository.save(any(User.class))).willThrow(new RuntimeException()); // Unique Exception

        assertThrows(RuntimeException.class,() -> authService.signUp(request));
    }

    @Test
    @DisplayName("Login Success Test")
    void loginSuccessTest(){
        LoginRequest request = new LoginRequest(email, password);

        User saveUser = User.builder()
                .id(1L)
                .email(email)
                .password(encryptUtil.encryptPassword(password))
                .build();


        given(userRepository.findByEmailAndPassword(email,encryptUtil.encryptPassword(password)))
                .willReturn(Optional.of(saveUser));

        given(jwtService.generateToken(1L)).willReturn(tokenResponse);

        TokenResponse response = authService.login(request);

        assertEquals(tokenResponse,response);
    }

    @Test
    @DisplayName("Login Failure By Invalid Value Test")
    void loginFailureByInvalidValueTest(){
        LoginRequest request = new LoginRequest(email, password);

        given(userRepository.findByEmailAndPassword(email,encryptUtil.encryptPassword(password)))
                .willThrow(new UserNotFoundException());

        assertThrows(UserNotFoundException.class, () -> authService.login(request));
    }
}
