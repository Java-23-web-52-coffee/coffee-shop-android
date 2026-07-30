package edu.cnm.deepdive.coffeeshop.service;

import edu.cnm.deepdive.coffeeshop.model.entity.Favorite;
import edu.cnm.deepdive.coffeeshop.model.entity.FavoriteId;
import edu.cnm.deepdive.coffeeshop.model.entity.Profile;
import edu.cnm.deepdive.coffeeshop.model.entity.Shop;
import edu.cnm.deepdive.coffeeshop.repository.FavoriteRepository;
import edu.cnm.deepdive.coffeeshop.repository.ProfileRepository;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteServiceImpl implements FavoriteService {

  private final ProfileRepository profileRepository;
  private final FavoriteRepository favoriteRepository;

  @Autowired
  public FavoriteServiceImpl(ProfileRepository profileRepository,
      FavoriteRepository favoriteRepository) {
    this.profileRepository = profileRepository;
    this.favoriteRepository = favoriteRepository;
  }

  @Override
  public Set<Favorite> getFavorites(Profile profile) {
    return profile.getFavorites();
  }

  @Override
  public void saveFavorite(Shop shop, Profile profile) {
    Favorite favorite = new Favorite();
    favorite.setShop(shop);
    favorite.setProfile(profile);
    profileRepository.save(profile);
  }

  @Override
  public void removeFavorite(Shop shop, Profile profile) {
    FavoriteId favoriteId = new FavoriteId(profile.getId(), shop.getId());
    // TODO: 7/30/26 Explore simplification.
    favoriteRepository.findById(favoriteId)
        .map((favorite) -> {
          Profile updatedProfile = favorite.getProfile();
          updatedProfile.getFavorites().remove(favorite);
          profileRepository.save(updatedProfile);
          favoriteRepository.delete(favorite);
          return null;
        });
  }

}
