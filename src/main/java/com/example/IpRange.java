package com.example;

import lombok.Data;

@Data
public class IpRange {
  private String ipPrefix;
  public String region;
  private String cidr;

  public IpRange(String region, String cidr) {
    this.region = region;
    this.cidr = cidr;
  }

  public String getRegion() {
    return region;
  }

  public String getCidr() {
    return cidr;
  }
}