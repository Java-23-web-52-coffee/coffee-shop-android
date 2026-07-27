/* Copyright 2026 CNM Ingenuity, Inc. Licensed under the Apache License, Version 2.0. */
package edu.cnm.deepdive.coffeeshop.model.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class RatingDto {

  private UUID visitId;
  private UUID interestId;
  private BigDecimal value;

  public UUID getVisitId() { return visitId; }
  public void setVisitId(UUID visitId) { this.visitId = visitId; }
  public UUID getInterestId() { return interestId; }
  public void setInterestId(UUID interestId) { this.interestId = interestId; }
  public BigDecimal getValue() { return value; }
  public void setValue(BigDecimal value) { this.value = value; }
}
