package edu.cnm.deepdive.coffeeshop.controller;

import edu.cnm.deepdive.coffeeshop.controller.api.FavoriteApi;
import edu.cnm.deepdive.coffeeshop.model.dto.FavoriteRequestDto;
import edu.cnm.deepdive.coffeeshop.model.dto.ShopDto;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FavoriteController implements FavoriteApi {

  @Override
  public ResponseEntity<Void> createFavorite(FavoriteRequestDto favoriteRequestDto) {
    return null;
  }

  @Override
  public ResponseEntity<Void> deleteFavorite(UUID shopId) {
    return null;
  }

  @Override
  public ResponseEntity<List<ShopDto>> listMyFavorites() {
    return null;
  }
}
