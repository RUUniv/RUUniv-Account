package com.ruunivaccountserver.app.apikey.api;

import com.ruunivaccountserver.app.apikey.dto.ApiKeyResponse.ApiKeysInfo;
import com.ruunivaccountserver.app.apikey.service.interfaces.ApiKeyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "API 키", description = "API 정보를 조회 하고 관리 합니다")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiKeyController {
    private final ApiKeyService apiKeyService;

    @PostMapping("/api/v1/apiKeys")
    public ResponseEntity<Void> createApiKey(@RequestHeader("passport") Long passport) {
        apiKeyService.createApiKey(passport);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/v1/apiKeys")
    public ResponseEntity<Void> deleteApiKey(@RequestHeader("passport") Long passport, @RequestParam String apiKey) {
        apiKeyService.deleteApiKey(passport, apiKey);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/v1/apiKeys")
    public ResponseEntity<ApiKeysInfo> getApiKeysInfo(@RequestHeader("passport") Long passport) {
        ApiKeysInfo response = apiKeyService.getApiKeysInfo(passport);
        return ResponseEntity.ok(response);
    }
}
