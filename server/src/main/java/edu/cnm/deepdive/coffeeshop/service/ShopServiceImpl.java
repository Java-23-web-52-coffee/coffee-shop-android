package edu.cnm.deepdive.coffeeshop.service;

import edu.cnm.deepdive.coffeeshop.model.entity.Profile;
import edu.cnm.deepdive.coffeeshop.model.entity.Shop;
import edu.cnm.deepdive.coffeeshop.repository.ShopRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {

  private final ShopRepository repository;

  @Autowired
  public ShopServiceImpl(ShopRepository repository) {
    this.repository = repository;
  }

  @Override
  public Shop getShop(UUID id) {
    return repository.findById(id)
        .orElseThrow();
  }

  @Override
  public Shop saveShop(Shop shop, Profile profile) {
    return repository.save(shop);
  }

  @Override
  public List<Shop> getAllShops() {
    return repository.findAll();
  }

}
