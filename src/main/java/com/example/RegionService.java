package com.example;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.stream.Collectors; 
import java.io.IOException;
import java.util.List;

@Service
public class RegionService {

  private final String API_URL = "https://ip-ranges.amazonaws.com/ip-ranges.json";

  public String getRangesForRegion(String region) {
    
    String response = WebClient.create()
      .get()
      .uri(API_URL)
      .retrieve()
      .bodyToMono(String.class)
      .block();
      
    ObjectMapper mapper = new ObjectMapper();

    try {

        // Parse JSON into list of IP ranges  
        List<IpRange> ranges = mapper.readValue(response, new TypeReference<List<IpRange>>(){});

        // Filter by region parameter
        List<IpRange> filtered = ranges.stream()
            .filter(r -> r.region.equals(region))
            .collect(Collectors.toList());

        // Map filtered ranges to strings and join with newline
        String filteredRanges = filtered.stream()
            .map(IpRange::getCidr) 
            .collect(Collectors.joining("\n"));

        return filteredRanges;

    } catch (IOException e) {
        throw new RuntimeException("Error parsing JSON", e);
    }
  }

}