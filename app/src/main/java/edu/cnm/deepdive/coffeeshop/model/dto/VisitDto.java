/* Copyright 2026 CNM Ingenuity, Inc. Licensed under the Apache License, Version 2.0. */
package edu.cnm.deepdive.coffeeshop.model.dto;

import java.util.List;
import java.util.UUID;

public class VisitDto {

  private UUID id;
  private UUID shopId;
  private UUID profileId;
  private String createdAt;
  private List<RatingDto> ratings;

  public UUID getId() { return id; }
  public void setId(UUID id) { this.id = id; }
  public UUID getShopId() { return shopId; }
  public void setShopId(UUID shopId) { this.shopId = shopId; }
  public UUID getProfileId() { return profileId; }
  public void setProfileId(UUID profileId) { this.profileId = profileId; }
  public String getCreatedAt() { return createdAt; }
  public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
  public List<RatingDto> getRatings() { return ratings; }
  public void setRatings(List<RatingDto> ratings) { this.ratings = ratings; }
}
