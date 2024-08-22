package com.ruunivaccountserver.infrastructure.feign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class VerificationServerApiKeysResponse {
    private String apiKey;
    private Long apiKeyId;
}
