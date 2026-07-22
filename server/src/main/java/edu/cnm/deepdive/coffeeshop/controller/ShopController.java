package edu.cnm.deepdive.coffeeshop.controller;


import edu.cnm.deepdive.coffeeshop.model.entity.Shop;
import edu.cnm.deepdive.coffeeshop.repository.ShopRepository;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shops")
public class ShopController {

  private final ShopRepository shopRepository;

  public ShopController(ShopRepository shopRepository) {
    this.shopRepository = shopRepository;
  }

  @GetMapping
  public List<Shop> getAllShops() {
    return shopRepository.findAll();
  }

  @GetMapping("/{id}")
  public Shop getShopById(@PathVariable UUID id) {
    return shopRepository.findById(id).orElse(null);
  }

}
