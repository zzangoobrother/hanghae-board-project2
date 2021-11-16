package com.sparta.hanghaeboardproject2.controller;

import com.sparta.hanghaeboardproject2.service.RedisTestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisTestController {

  private final RedisTestService redisTestService;

  public RedisTestController(RedisTestService redisTestService) {
    this.redisTestService = redisTestService;
  }

  @GetMapping("/redis/test")
  public String redisTest() {
    redisTestService.redisTest();
    return "/";
  }

  @PostMapping("/redis/test/{key}")
  public String redisGetTest(@PathVariable String key) {
    redisTestService.redisGetTest(key);
    return "/";
  }
}
