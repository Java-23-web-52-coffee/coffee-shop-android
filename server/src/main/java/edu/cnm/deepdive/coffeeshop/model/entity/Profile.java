package edu.cnm.deepdive.coffeeshop.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import org.hibernate.Hibernate;

@Entity
@Table(name = "profile")
public class Profile {

  @Id
  @GeneratedValue
  private UUID id;

  @Column(name = "activation_token", columnDefinition = "bpchar(32)")
  private String activationToken;

  @Column(nullable = false, unique = true, length = 127)
  private String email;

  @Column(nullable = false, length = 63)
  private String name;

  @Column(name = "password_hash", nullable = false, columnDefinition = "bpchar(97)")
  private String passwordHash;

  @OneToMany(mappedBy = "profile", fetch = FetchType.LAZY)
  @OrderBy("createdAt desc")
  private Set<Visit> visits = new LinkedHashSet<>();

  @OneToMany(mappedBy = "profile", fetch = FetchType.EAGER)
  private Set<Favorite> favorites = new LinkedHashSet<>();

  @OneToMany(mappedBy = "profile", fetch = FetchType.EAGER)
  private Set<Preference> preferences = new LinkedHashSet<>();

  @OneToMany(mappedBy = "profile", fetch = FetchType.LAZY)
  @OrderBy("createdAt desc")
  private Set<Review> reviews = new LinkedHashSet<>();

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getActivationToken() {
    return activationToken;
  }

  public void setActivationToken(String activationToken) {
    this.activationToken = activationToken;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPasswordHash() {
    return passwordHash;
  }

  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }

  public Set<Visit> getVisits() {
    return visits;
  }

  public void setVisits(Set<Visit> visits) {
    this.visits = visits;
  }

  public Set<Favorite> getFavorites() {
    return favorites;
  }

  public void setFavorites(Set<Favorite> favorites) {
    this.favorites = favorites;
  }

  public Set<Preference> getPreferences() {
    return preferences;
  }

  public void setPreferences(Set<Preference> preferences) {
    this.preferences = preferences;
  }

  public Set<Review> getReviews() {
    return reviews;
  }

  public void setReviews(Set<Review> reviews) {
    this.reviews = reviews;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || Hibernate.getClass(this) != Hibernate.getClass(obj)) {
      return false;
    }
    Profile other = (Profile) obj;
    return id != null && Objects.equals(id, other.id);
  }

  @Override
  public int hashCode() {
    return Hibernate.getClass(this).hashCode();
  }

  @Override
  public String toString() {
    return "Profile{id=" + id + ", email='" + email + "', name='" + name + "'}";
  }
}
