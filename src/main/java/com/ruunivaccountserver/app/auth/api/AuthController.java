package com.ruunivaccountserver.app.auth.api;

import com.ruunivaccountserver.app.auth.dto.AuthRequest.LoginRequest;
import com.ruunivaccountserver.app.auth.dto.AuthRequest.SignUpRequest;
import com.ruunivaccountserver.app.auth.dto.AuthResponse.TokenResponse;
import com.ruunivaccountserver.app.auth.service.AuthService;
import com.ruunivaccountserver.app.auth.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/api/v1/auth/signUp")
    public ResponseEntity<TokenResponse> signUp(@RequestBody SignUpRequest request){
        TokenResponse response = authService.signUp(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/v1/auth/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request){
        TokenResponse response = authService.login(request);

        return ResponseEntity.ok(response);
    }

//    @PostMapping("/api/v1/auth/refresh")
//    public ResponseEntity<TokenResponse> refresh(@RequestBody String refreshToken){
//        TokenResponse response = authService.
//
//        return ResponseEntity.ok(response);
//    }
}
