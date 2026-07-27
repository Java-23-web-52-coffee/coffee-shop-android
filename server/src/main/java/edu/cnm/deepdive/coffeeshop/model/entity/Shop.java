package edu.cnm.deepdive.coffeeshop.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import org.hibernate.Hibernate;

@Entity
@Table(name = "shop")
public class Shop {

  @Id
  @GeneratedValue
  private UUID id;

  @Column(length = 255)
  private String address;

  @Column(columnDefinition = "json", nullable = false)
  private String hours;

  @Column(precision = 9, scale = 6)
  private BigDecimal lat;

  @Column(precision = 9, scale = 6)
  private BigDecimal lng;

  @Column(nullable = false, length = 63)
  private String name;

  @Column(length = 31)
  private String phone;

  @Column(name = "image_url", length = 255)
  private String imageUrl;

  @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
  private Set<Visit> visits = new LinkedHashSet<>();

  @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
  private Set<Favorite> favorites = new LinkedHashSet<>();

  @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
  private Set<Review> reviews = new LinkedHashSet<>();

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getHours() {
    return hours;
  }

  public void setHours(String hours) {
    this.hours = hours;
  }

  public BigDecimal getLat() {
    return lat;
  }

  public void setLat(BigDecimal lat) {
    this.lat = lat;
  }

  public BigDecimal getLng() {
    return lng;
  }

  public void setLng(BigDecimal lng) {
    this.lng = lng;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
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
    Shop other = (Shop) obj;
    return id != null && Objects.equals(id, other.id);
  }

  @Override
  public int hashCode() {
    return Hibernate.getClass(this).hashCode();
  }

  @Override
  public String toString() {
    return "Shop{id=" + id + ", name='" + name + "', address='" + address + "'}";
  }
}
