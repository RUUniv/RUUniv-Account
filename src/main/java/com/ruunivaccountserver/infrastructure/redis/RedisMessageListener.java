package com.ruunivaccountserver.infrastructure.redis;

import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisMessageListener implements MessageListener {

    private final List<CacheManager> cacheManagerList;

    @Override
    public void onMessage(final Message message, final byte[] pattern) {
        final var evictKey = new String(message.getBody(), StandardCharsets.UTF_8);

        for (final CacheManager cacheManager : cacheManagerList) {
            cacheManager.getCache("Cache").evict(evictKey);
        }
    }
}