package com.sparta.hanghaeboardproject2.service;

import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisTestService {

  private final StringRedisTemplate redisTemplate;

  public RedisTestService(StringRedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public void redisTest() {
    final ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
    stringStringValueOperations.set("username", "jwtToken");

    redisTemplate.expire("username", 30, TimeUnit.SECONDS);
  }
}
