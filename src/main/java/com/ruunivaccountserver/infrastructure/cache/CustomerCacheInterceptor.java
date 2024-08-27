package com.ruunivaccountserver.infrastructure.cache;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.interceptor.CacheInterceptor;
import org.springframework.data.redis.cache.RedisCache;

@RequiredArgsConstructor
@Slf4j
public class CustomerCacheInterceptor extends CacheInterceptor {

    private final CacheManager l1CacheManager;
    private final CacheManager l2RedisCacheManager;

    @Override
    protected Cache.ValueWrapper doGet(Cache cache, Object key) {
        Cache.ValueWrapper existingCacheValue = super.doGet(cache, key);

        if (existingCacheValue != null && cache.getClass() == RedisCache.class) {
            log.info("caffeine");
            Cache caffeineCache = l1CacheManager.getCache(cache.getName());
            if (caffeineCache != null) {
                caffeineCache.putIfAbsent(key, existingCacheValue.get());
            }
        } else if (existingCacheValue != null && cache.getClass() == CaffeineCache.class) {
            log.info("redis");
            Cache redisCache = l2RedisCacheManager.getCache(cache.getName());
            if (redisCache != null) {
                log.info("redisredis");
                redisCache.putIfAbsent(key, existingCacheValue.get());
            }
        }

        return existingCacheValue;
    }
}