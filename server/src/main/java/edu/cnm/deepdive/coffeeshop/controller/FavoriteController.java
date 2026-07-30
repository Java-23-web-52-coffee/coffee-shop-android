package edu.cnm.deepdive.coffeeshop.controller;

import edu.cnm.deepdive.coffeeshop.controller.api.FavoriteApi;
import edu.cnm.deepdive.coffeeshop.model.dto.FavoriteRequestDto;
import edu.cnm.deepdive.coffeeshop.model.dto.ShopDto;
import edu.cnm.deepdive.coffeeshop.model.entity.Profile;
import edu.cnm.deepdive.coffeeshop.service.FavoriteService;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FavoriteController implements FavoriteApi {

  private final FavoriteService favoriteService;

  public FavoriteController(FavoriteService favoriteService) {
    this.favoriteService = favoriteService;
  }

  @Override
  public ResponseEntity<Void> createFavorite(FavoriteRequestDto favoriteRequestDto) {
     favoriteService.saveFavorite(favoriteRequestDto, getCurrentProfile());
    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<Void> deleteFavorite(UUID shopId) {
    favoriteService.removeFavorite(shopId, getCurrentProfile());
    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<List<ShopDto>> listMyFavorites() {
    return ResponseEntity.ok(favoriteService.getFavorites(getCurrentProfile()));
  }

  private Profile getCurrentProfile() {
    //noinspection DataFlowIssue
    return (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }

}
