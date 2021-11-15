package com.sparta.hanghaeboardproject2.controller;

import com.sparta.hanghaeboardproject2.service.RedisTestService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisTestController {

  private final RedisTestService redisTestService;

  public RedisTestController(RedisTestService redisTestService) {
    this.redisTestService = redisTestService;
  }

  @PostMapping("/redis/test")
  public void redisTest() {
    redisTestService.redisTest();
  }
}
