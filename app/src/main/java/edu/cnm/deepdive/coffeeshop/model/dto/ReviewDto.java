/* Copyright 2026 CNM Ingenuity, Inc. Licensed under the Apache License, Version 2.0. */
package edu.cnm.deepdive.coffeeshop.model.dto;

import java.util.UUID;

public class ReviewDto {

  private UUID id;
  private UUID shopId;
  private ProfileDto author;
  private int rating;
  private String comment;
  private String createdAt;
  private String updatedAt;

  public UUID getId() { return id; }
  public void setId(UUID id) { this.id = id; }
  public UUID getShopId() { return shopId; }
  public void setShopId(UUID shopId) { this.shopId = shopId; }
  public ProfileDto getAuthor() { return author; }
  public void setAuthor(ProfileDto author) { this.author = author; }
  public int getRating() { return rating; }
  public void setRating(int rating) { this.rating = rating; }
  public String getComment() { return comment; }
  public void setComment(String comment) { this.comment = comment; }
  public String getCreatedAt() { return createdAt; }
  public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
  public String getUpdatedAt() { return updatedAt; }
  public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
}
