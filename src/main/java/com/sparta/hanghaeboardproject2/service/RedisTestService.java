package com.sparta.hanghaeboardproject2.service;

import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisTestService {

  private final RedisTemplate<String, Object> redisTemplate;

  public RedisTestService(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public void redisTest() {
    final ValueOperations<String, Object> stringStringValueOperations = redisTemplate.opsForValue();
    stringStringValueOperations.set("username", "홍길동");

    redisTemplate.expire("username", 30, TimeUnit.SECONDS);
  }

  public void redisGetTest(String key) {
    final ValueOperations<String, Object> stringStringValueOperations = redisTemplate.opsForValue();
    String result = (String) stringStringValueOperations.get(key);
    System.out.println(result);
  }
}
