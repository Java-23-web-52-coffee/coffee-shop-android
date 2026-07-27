package edu.cnm.deepdive.coffeeshop.service;

import edu.cnm.deepdive.coffeeshop.model.entity.Profile;
import edu.cnm.deepdive.coffeeshop.model.entity.Shop;
import java.util.List;
import java.util.UUID;

public interface ShopService {

  Shop getShop(UUID id);

  Shop saveShop(Shop shop, Profile profile);

  List<Shop> getAllShops();

}
