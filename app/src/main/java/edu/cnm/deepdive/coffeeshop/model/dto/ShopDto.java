/* Copyright 2026 CNM Ingenuity, Inc. Licensed under the Apache License, Version 2.0. */
package edu.cnm.deepdive.coffeeshop.model.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class ShopDto {

  private UUID id;
  private String address;
  private String hours;
  private BigDecimal latitude;
  private BigDecimal longitude;
  private String name;
  private String phone;
  private String imageUrl;

  public UUID getId() { return id; }
  public void setId(UUID id) { this.id = id; }
  public String getAddress() { return address; }
  public void setAddress(String address) { this.address = address; }
  public String getHours() { return hours; }
  public void setHours(String hours) { this.hours = hours; }
  public BigDecimal getLatitude() { return latitude; }
  public void setLatitude(BigDecimal latitude) { this.latitude = latitude; }
  public BigDecimal getLongitude() { return longitude; }
  public void setLongitude(BigDecimal longitude) { this.longitude = longitude; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getPhone() { return phone; }
  public void setPhone(String phone) { this.phone = phone; }
  public String getImageUrl() { return imageUrl; }
  public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
