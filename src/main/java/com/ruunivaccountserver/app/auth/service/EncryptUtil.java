package com.ruunivaccountserver.app.auth.service;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
import org.springframework.stereotype.Component;

@Component
public class EncryptUtil {
    public String encryptPassword(String password){
        return Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
    }
}
