package com.ruunivaccountserver.app.user.service;

import com.ruunivaccountserver.app.user.dto.UserResponse.UserInfo;
import com.ruunivaccountserver.app.user.entity.User;
import com.ruunivaccountserver.app.user.repository.UserRepository;
import com.ruunivaccountserver.common.error.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserInfo getUserInfo(Long id){
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        return UserInfo.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }
}
