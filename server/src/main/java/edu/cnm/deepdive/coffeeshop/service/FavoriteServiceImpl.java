package edu.cnm.deepdive.coffeeshop.service;

import edu.cnm.deepdive.coffeeshop.model.entity.Favorite;
import edu.cnm.deepdive.coffeeshop.model.entity.Profile;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteServiceImpl implements FavoriteService {

  private final FavoriteService service;

  @Autowired
  public FavoriteServiceImpl(FavoriteService service) {
    this.service = service;
  }

  @Override
  public List<Favorite> getFavorites(Profile profile) {
    return service.getFavorites(profile);
  }

  @Override
  public Favorite saveFavorite(Favorite favorite, Profile profile) {
    return service.saveFavorite(favorite, profile);
  }

  @Override
  public Favorite removeFavorite(UUID shopId, Profile profile) {
    return service.removeFavorite(shopId, profile);
  }

}
