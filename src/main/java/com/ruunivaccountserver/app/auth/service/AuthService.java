package com.ruunivaccountserver.app.auth.service;

import com.netflix.discovery.provider.Serializer;
import com.ruunivaccountserver.app.auth.dto.AuthRequest.LoginRequest;
import com.ruunivaccountserver.app.auth.dto.AuthRequest.SignUpRequest;
import com.ruunivaccountserver.app.auth.dto.AuthResponse.TokenResponse;
import com.ruunivaccountserver.app.user.entity.User;
import com.ruunivaccountserver.app.user.repository.UserRepository;
import com.ruunivaccountserver.common.error.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final EncryptUtil encryptUtil;

    public TokenResponse signUp(SignUpRequest request){
        User user = User.builder()
                .email(request.getEmail())
                .password(encryptUtil.encryptPassword(request.getPassword()))
                .build();

        User saveUser = userRepository.save(user);

        return jwtService.generateToken(saveUser.getId());
    }

    public TokenResponse login(LoginRequest request){
        User user = userRepository.findByEmailAndPassword(request.getEmail(),
                encryptUtil.encryptPassword(request.getPassword()))
                .orElseThrow(UserNotFoundException::new);

        return jwtService.generateToken(user.getId());
    }
}
