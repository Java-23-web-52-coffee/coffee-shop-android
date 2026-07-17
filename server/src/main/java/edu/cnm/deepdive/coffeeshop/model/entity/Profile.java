package edu.cnm.deepdive.coffeeshop.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "profile")
public class Profile {

  @Id
  @GeneratedValue
  private UUID id;

  @Column(name = "activation_token", length = 32, columnDefinition = "char(32)")
  private String activationToken;

  @Column(nullable = false, unique = true, length = 127)
  private String email;

  @Column(nullable = false, length = 63)
  private String name;

  @Column(name = "password_hash", nullable = false, length = 97, columnDefinition = "char(97)")
  private String passwordHash;

  @OneToMany(mappedBy = "profile")
  private Set<Visit> visits = new LinkedHashSet<>();

  @OneToMany(mappedBy = "profile")
  private Set<Favorite> favorites = new LinkedHashSet<>();

  @OneToMany(mappedBy = "profile")
  private Set<Preference> preferences = new LinkedHashSet<>();

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
}
