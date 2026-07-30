package edu.cnm.deepdive.coffeeshop.service;

import edu.cnm.deepdive.coffeeshop.model.entity.Favorite;
import edu.cnm.deepdive.coffeeshop.model.entity.Profile;
import edu.cnm.deepdive.coffeeshop.model.entity.Shop;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.ToDoubleBiFunction;

public interface FavoriteService {

  Set<Favorite> getFavorites(Profile profile);

  void saveFavorite(Shop shop, Profile profile);
  // TODO: 7/30/26 Verify that returning nothing is okay.

  void removeFavorite(Shop shop, Profile profile);
  // TODO: 7/30/26 Verify that returning nothing is okay.
}
