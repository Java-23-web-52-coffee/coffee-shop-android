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
@Table(name = "interest")
public class Interest {

  @Id
  @GeneratedValue
  private UUID id;

  @Column(length = 127)
  private String category;

  @OneToMany(mappedBy = "interest")
  private Set<Preference> preferences = new LinkedHashSet<>();

  @OneToMany(mappedBy = "interest")
  private Set<Rating> ratings = new LinkedHashSet<>();

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public Set<Preference> getPreferences() {
    return preferences;
  }

  public void setPreferences(Set<Preference> preferences) {
    this.preferences = preferences;
  }

  public Set<Rating> getRatings() {
    return ratings;
  }

  public void setRatings(Set<Rating> ratings) {
    this.ratings = ratings;
  }
}
