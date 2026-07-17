package edu.cnm.deepdive.coffeeshop.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class PreferenceId implements Serializable {

  @Column(name = "profile_id", nullable = false)
  private UUID profileId;

  @Column(name = "interest_id", nullable = false)
  private UUID interestId;

  public PreferenceId() {
  }

  public PreferenceId(UUID profileId, UUID interestId) {
    this.profileId = profileId;
    this.interestId = interestId;
  }

  public UUID getProfileId() {
    return profileId;
  }

  public void setProfileId(UUID profileId) {
    this.profileId = profileId;
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
    if (!(obj instanceof PreferenceId other)) {
      return false;
    }
    return Objects.equals(profileId, other.profileId) && Objects.equals(interestId, other.interestId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(profileId, interestId);
  }
}
