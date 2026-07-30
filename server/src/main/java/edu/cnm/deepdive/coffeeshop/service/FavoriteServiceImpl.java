package edu.cnm.deepdive.coffeeshop.service;

import edu.cnm.deepdive.coffeeshop.model.dto.FavoriteRequestDto;
import edu.cnm.deepdive.coffeeshop.model.dto.ShopDto;
import edu.cnm.deepdive.coffeeshop.model.entity.Profile;
import edu.cnm.deepdive.coffeeshop.repository.ProfileRepository;
import edu.cnm.deepdive.coffeeshop.repository.ShopRepository;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteServiceImpl implements FavoriteService {

  private final ProfileRepository profileRepository;
  private final ShopRepository shopRepository;
  private final ShopService shopService;

  @Autowired
  public FavoriteServiceImpl(ProfileRepository profileRepository,
      ShopRepository shopRepository, ShopService shopService) {
    this.profileRepository = profileRepository;
    this.shopRepository = shopRepository;
    this.shopService = shopService;
  }

  @Override
  public List<ShopDto> getFavorites(Profile profile) {
    return profile.getFavorites()
        .stream()
        .map(shopService::buildShopDto)
        .toList();
  }

  @Override
  public void saveFavorite(FavoriteRequestDto dto, Profile profile) {
    shopRepository.findById(dto.getShopId())
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
