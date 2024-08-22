package com.ruunivaccountserver.infrastructure.feign;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class VerificationServerApi {

    @Component
    @FeignClient(name = "RUUNIV-VERIFICATION-SERVER")
    public interface VerificationServerClient {
        @GetMapping("/v1/keys/me")
        List<VerificationServerApiKeysResponse> getApiKeys(@RequestParam Long userId);
    }
}
