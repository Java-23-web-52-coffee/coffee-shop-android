package edu.cnm.deepdive.coffeeshop.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "preference")
public class Preference {

  @EmbeddedId
  private PreferenceId id;

  @MapsId("profileId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "profile_id", nullable = false)
  private Profile profile;

  @MapsId("interestId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "interest_id", nullable = false)
  private Interest interest;

  @Column
  private BigDecimal importance;

  public PreferenceId getId() {
    return id;
  }

  public void setId(PreferenceId id) {
    this.id = id;
  }

  public Profile getProfile() {
    return profile;
  }

  public void setProfile(Profile profile) {
    this.profile = profile;
  }

  public Interest getInterest() {
    return interest;
  }

  public void setInterest(Interest interest) {
    this.interest = interest;
  }

  public BigDecimal getImportance() {
    return importance;
  }

  public void setImportance(BigDecimal importance) {
    this.importance = importance;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    return obj instanceof Preference other && id != null && Objects.equals(id, other.id);
  }

  @Override
  public int hashCode() {
    return Preference.class.hashCode();
  }

  @Override
  public String toString() {
    return "Preference{id=" + id + ", importance=" + importance + "}";
  }
}
