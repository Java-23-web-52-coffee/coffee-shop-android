package edu.cnm.deepdive.coffeeshop.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class FavoriteId implements Serializable {

  @Column(name = "profile_id", nullable = false)
  private UUID profileId;

  @Column(name = "shop_id", nullable = false)
  private UUID shopId;

  public FavoriteId() {
  }

  public FavoriteId(UUID profileId, UUID shopId) {
    this.profileId = profileId;
    this.shopId = shopId;
  }

  public UUID getProfileId() {
    return profileId;
  }

  public void setProfileId(UUID profileId) {
    this.profileId = profileId;
  }

  public UUID getShopId() {
    return shopId;
  }

  public void setShopId(UUID shopId) {
    this.shopId = shopId;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof FavoriteId other)) {
      return false;
    }
    return Objects.equals(profileId, other.profileId) && Objects.equals(shopId, other.shopId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(profileId, shopId);
  }
}
