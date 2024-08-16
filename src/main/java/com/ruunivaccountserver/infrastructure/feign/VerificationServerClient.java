package com.ruunivaccountserver.infrastructure.feign;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class VerificationServerClient {

    @Component
    @FeignClient(name = "StudentVerifyApi", url = "https://ruuniv-server.xyz/v1")
    public interface StudentVerifyApiClient {

        @GetMapping("/verification/apiKey")
        List<String> getApiKeys(@RequestParam Long userId);
    }
}
