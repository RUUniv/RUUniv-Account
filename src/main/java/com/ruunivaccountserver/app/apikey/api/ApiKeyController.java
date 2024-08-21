package com.ruunivaccountserver.app.apikey.api;

import com.ruunivaccountserver.app.apikey.dto.ApiKeyRequest.ApiKeyCreateRequest;
import com.ruunivaccountserver.app.apikey.dto.ApiKeyRequest.ApiKeyDeleteRequest;
import com.ruunivaccountserver.app.apikey.dto.ApiKeyResponse.ApiKeysResponse;
import com.ruunivaccountserver.app.apikey.service.ApiKeyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "API 키" , description = "API 정보를 조회 하고 관리 합니다")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiKeyController {
    private final ApiKeyService apiKeyService;

    @PostMapping("/api/v1/apiKeys")
    public ResponseEntity<Void> createApiKey(@RequestBody ApiKeyCreateRequest request){
        apiKeyService.createApiKey(request.getUserId());

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/v1/apiKeys")
    public ResponseEntity<Void> deleteApiKey(@RequestParam ApiKeyDeleteRequest request){
        apiKeyService.deleteApiKey(request.getUserId(),request.getApiKey());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/v1/apiKeys/{userId}")
    public ResponseEntity<ApiKeysResponse> getApiKeys(@PathVariable Long userId){
        ApiKeysResponse response = apiKeyService.getApiKeys(userId);

        return ResponseEntity.ok(response);
    }
}
