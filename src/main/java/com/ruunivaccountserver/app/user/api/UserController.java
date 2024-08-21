package com.ruunivaccountserver.app.user.api;

import com.ruunivaccountserver.app.user.dto.UserResponse.UserInfo;
import com.ruunivaccountserver.app.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원" , description = "회원정보를 제공하고 , 관리합니다")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/api/v1/users/me/{userId}")
    public ResponseEntity<UserInfo> getUserInfo(@PathVariable Long userId){
        UserInfo userInfo = userService.getUserInfo(userId);

        return ResponseEntity.ok(userInfo);
    }
}
