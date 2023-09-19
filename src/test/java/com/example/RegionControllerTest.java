package com.example;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(RegionController.class)  
class RegionControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private RegionService service;

  @Test
  void getRanges() throws Exception {
    when(service.getRangesForRegion("EU")).thenReturn("1.2.3.0/24");
    
    mvc.perform(get("/?region=EU"))
      .andExpect(status().isOk())
      .andExpect(content().string("1.2.3.0/24"));
  }

}