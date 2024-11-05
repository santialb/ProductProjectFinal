package com.example.finalExam.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


import java.util.EnumSet;

@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfiguration {

    private static final long CACHE_EVICT_DELAY_MS = 300_000;  // 5 minutes

    @Bean
    public CacheManager cacheManager(){
        ConcurrentMapCacheManager manager = new ConcurrentMapCacheManager();
        manager.setCacheNames(EnumSet.allOf(CacheName.class).stream().map(CacheName::getValue).toList());
        return manager;
    }

    @CacheEvict(value = "products", allEntries = true)
    @Scheduled(fixedDelay = CACHE_EVICT_DELAY_MS, initialDelay = 0)
    public void evictProductCache(){
        System.out.println("Evicting Product Cache");
    }

}
