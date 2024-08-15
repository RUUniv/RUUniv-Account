package com.ruunivaccountserver.app.auth.service;

import com.ruunivaccountserver.app.auth.dto.AuthResponse.TokenResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Calendar;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.access.exp}")
    private Long accessExp;

    @Value("${jwt.refresh.exp}")
    private Long refreshExp;

    public TokenResponse generateToken(Long id){
        String accessToken = generateAccessToken(id);
        String refreshToken = generateRefreshToken(id);

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private String generateAccessToken(Long id){
        return Jwts.builder()
                .claim("userId",id)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + accessExp))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    private String generateRefreshToken(Long id){
        return Jwts.builder()
                .claim("userId",id)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + refreshExp))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
