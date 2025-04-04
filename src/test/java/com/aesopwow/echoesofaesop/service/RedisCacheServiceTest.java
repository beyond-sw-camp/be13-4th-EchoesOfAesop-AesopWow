package com.aesopwow.echoesofaesop.service;

import com.aesopwow.echoesofaesop.common.cache.CacheService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class RedisCacheServiceTest {
    @Autowired
    private CacheService cacheService;

    private static List<String> keys = new ArrayList<>();

    @AfterEach
    void afterAll() {
        for (String key : keys) {
            cacheService.delete(key);
        }
        keys.clear();
    }

    @Test
    void setAndGetTest() {
        cacheService.set("test", "test", 10);
        String value = cacheService.get("test");
        assertThat(value).isEqualTo("test");
        keys.add("test");
    }

    @Test
    void getTestNotExistKey() {
        String value = cacheService.get("test");
        assertThat(value).isNull();
    }
}
