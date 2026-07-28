package edu.cnm.deepdive.coffeeshop.service;

import edu.cnm.deepdive.coffeeshop.model.entity.Favorite;
import edu.cnm.deepdive.coffeeshop.model.entity.Profile;
import java.util.List;
import java.util.UUID;

public interface FavoriteService {

  List<Favorite> getFavorites(Profile profile);

  Favorite saveFavorite(Favorite favorite, Profile profile);

  Favorite removeFavorite(UUID shopId, Profile profile);
}
