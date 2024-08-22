package com.ruunivaccountserver.app.user.service;

import com.ruunivaccountserver.app.user.dto.UserResponse.UserInfo;
import com.ruunivaccountserver.app.user.entity.User;
import com.ruunivaccountserver.app.user.repository.UserRepository;
import com.ruunivaccountserver.common.error.user.MaxApiKeyException;
import com.ruunivaccountserver.common.error.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private static final int API_KEY_COUNT_MAX = 3;

    public UserInfo getUserInfo(Long userId){
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        return UserInfo.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }

    public User getUser(Long userId){
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public void checkApiKeyCountMax(Long userId){
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        if(user.getApiKeyCount() >= API_KEY_COUNT_MAX){
            throw new MaxApiKeyException();
        }

        user.addApiKey();
        userRepository.save(user);
    }

    @Transactional
    public void addApiKey(Long userId){
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        user.addApiKey();
        userRepository.save(user);
    }

    @Transactional
    public void deleteApiKey(Long userId){
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        user.deleteApiKey();
        userRepository.save(user);
    }
}
