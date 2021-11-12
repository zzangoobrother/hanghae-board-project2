package com.sparta.hanghaeboardproject2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

  @GetMapping
  public String healthCheck() {
    return "health ok";
  }
}
