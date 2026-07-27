/* Copyright 2026 CNM Ingenuity, Inc. Licensed under the Apache License, Version 2.0. */
package edu.cnm.deepdive.coffeeshop.model.dto;

import java.util.UUID;

public class InterestDto {

  private UUID id;
  private String category;

  public UUID getId() { return id; }
  public void setId(UUID id) { this.id = id; }
  public String getCategory() { return category; }
  public void setCategory(String category) { this.category = category; }
}
