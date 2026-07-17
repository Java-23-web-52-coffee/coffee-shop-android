package edu.cnm.deepdive.coffeeshop.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
}
