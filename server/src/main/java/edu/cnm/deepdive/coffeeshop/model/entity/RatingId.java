package edu.cnm.deepdive.coffeeshop.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class RatingId implements Serializable {

  @Column(name = "visit_id", nullable = false)
  private UUID visitId;

  @Column(name = "interest_id", nullable = false)
  private UUID interestId;

  public RatingId() {
  }

  public RatingId(UUID visitId, UUID interestId) {
    this.visitId = visitId;
    this.interestId = interestId;
  }

  public UUID getVisitId() {
    return visitId;
  }

  public void setVisitId(UUID visitId) {
    this.visitId = visitId;
  }

  public UUID getInterestId() {
    return interestId;
  }

  public void setInterestId(UUID interestId) {
    this.interestId = interestId;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof RatingId other)) {
      return false;
    }
    return Objects.equals(visitId, other.visitId) && Objects.equals(interestId, other.interestId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(visitId, interestId);
  }
}
