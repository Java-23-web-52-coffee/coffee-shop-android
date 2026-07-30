package edu.cnm.deepdive.coffeeshop.service;

import edu.cnm.deepdive.coffeeshop.model.entity.Profile;
import edu.cnm.deepdive.coffeeshop.model.entity.Shop;
import edu.cnm.deepdive.coffeeshop.repository.ProfileRepository;
import edu.cnm.deepdive.coffeeshop.repository.ShopRepository;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteServiceImpl implements FavoriteService {

  private final ProfileRepository profileRepository;
  private final ShopRepository shopRepository;

  @Autowired
  public FavoriteServiceImpl(ProfileRepository profileRepository,
      ShopRepository shopRepository) {
    this.profileRepository = profileRepository;
    this.shopRepository = shopRepository;
  }

  @Override
  public Set<Shop> getFavorites(Profile profile) {
    return profile.getFavorites();
  }

  @Override
  public void saveFavorite(UUID shopId, Profile profile) {
    shopRepository.findById(shopId)
        .map((shop) -> profileRepository.findById(profile.getId())
            .map((p) -> {
              p.getFavorites().add(shop);
              return profileRepository.save(p);
            }))
        .orElseThrow();
  }

  @Override
  public void removeFavorite(UUID shopId, Profile profile) {
    shopRepository.findById(shopId)
        .map((shop) -> profileRepository.findById(profile.getId())
            .map((p) -> {
              p.getFavorites().remove(shop);
              return profileRepository.save(p);
            }))
        .orElseThrow();
  }

}
