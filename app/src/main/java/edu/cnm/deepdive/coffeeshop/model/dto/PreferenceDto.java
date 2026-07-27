/* Copyright 2026 CNM Ingenuity, Inc. Licensed under the Apache License, Version 2.0. */
package edu.cnm.deepdive.coffeeshop.model.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class PreferenceDto {

  private UUID profileId;
  private UUID interestId;
  private BigDecimal importance;

  public UUID getProfileId() { return profileId; }
  public void setProfileId(UUID profileId) { this.profileId = profileId; }
  public UUID getInterestId() { return interestId; }
  public void setInterestId(UUID interestId) { this.interestId = interestId; }
  public BigDecimal getImportance() { return importance; }
  public void setImportance(BigDecimal importance) { this.importance = importance; }
}
