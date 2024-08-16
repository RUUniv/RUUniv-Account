package com.ruunivaccountserver.app.apikey.api;

import com.ruunivaccountserver.app.apikey.dto.ApiKeyResponse.ApiKeysResponse;
import com.ruunivaccountserver.app.apikey.service.ApiKeyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiKeyController {
    private final ApiKeyService apiKeyService;

    @PostMapping("/api/v1/apiKeys")
    public ResponseEntity<Void> createApiKey(@RequestBody Long userId){
        apiKeyService.createApiKey(userId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/v1/apiKeys")
    public ResponseEntity<Void> deleteApiKey(@RequestParam Long userId, @RequestParam String apiKey){
        apiKeyService.deleteApiKey(userId,apiKey);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/v1/apiKeys/{userId}")
    public ResponseEntity<ApiKeysResponse> getApiKeys(@PathVariable Long userId){
        ApiKeysResponse response = apiKeyService.getApiKeys(userId);

        return ResponseEntity.ok(response);
    }
}
