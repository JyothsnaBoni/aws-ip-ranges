package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RegionServiceTest {

  @Autowired
  private RegionService service;

  @Test
  void getRangesForRegion() {
    String ranges = service.getRangesForRegion("EU");
    assertEquals("1.2.3.0/24", ranges);
  }

}
