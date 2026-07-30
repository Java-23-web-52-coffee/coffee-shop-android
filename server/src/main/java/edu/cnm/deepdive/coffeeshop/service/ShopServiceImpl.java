package edu.cnm.deepdive.coffeeshop.service;

import edu.cnm.deepdive.coffeeshop.model.dto.ShopDto;
import edu.cnm.deepdive.coffeeshop.model.entity.Profile;
import edu.cnm.deepdive.coffeeshop.model.entity.Shop;
import edu.cnm.deepdive.coffeeshop.repository.ShopRepository;
import java.net.URI;
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
  public ShopDto getShop(UUID id) {
    return repository.findById(id)
        .map(this::buildShopDto)
        .orElseThrow();
  }

  @Override
  public ShopDto saveShop(Shop shop, Profile profile) {
    return buildShopDto(repository.save(shop));
  }

  @Override
  public List<ShopDto> getAllShops() {
    return repository.findAll().stream()
        .map(this::buildShopDto)
        .toList();
  }

  public ShopDto buildShopDto(Shop shop) {
    ShopDto dto = new ShopDto();
    dto.setName(shop.getName());
    dto.setId(shop.getId());
    dto.setAddress(shop.getAddress());
    dto.setPhone(shop.getPhone());
    dto.setHours(shop.getHours());
    dto.setImageUrl(URI.create(shop.getImageUrl()));
    dto.setLat(shop.getLat().doubleValue());
    dto.setLng(shop.getLng().doubleValue());
    return dto;
  }
}
