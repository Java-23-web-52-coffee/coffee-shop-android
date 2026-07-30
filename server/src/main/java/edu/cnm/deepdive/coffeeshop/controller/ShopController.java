package edu.cnm.deepdive.coffeeshop.controller;

import edu.cnm.deepdive.coffeeshop.controller.api.ShopApi;
import edu.cnm.deepdive.coffeeshop.model.dto.ShopDto;
import edu.cnm.deepdive.coffeeshop.model.entity.Profile;
import edu.cnm.deepdive.coffeeshop.model.entity.Shop;
import edu.cnm.deepdive.coffeeshop.service.ShopService;
import java.util.List;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopController implements ShopApi {

  private final ShopService shopService;

  public ShopController(ShopService shopService) {
    this.shopService = shopService;
  }

  @Override
  public ResponseEntity<ShopDto> getShopById(@PathVariable UUID id) {
    return ResponseEntity.ok(shopService.getShop(id));
  }

  @Override
  public ResponseEntity<List<ShopDto>> listShops() {
    return ResponseEntity.ok(shopService.getAllShops());
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ShopDto> postShop(@RequestBody Shop shop, @AuthenticationPrincipal Profile profile) {
    return ResponseEntity.ok(shopService.saveShop(shop, profile));
  }

  private Profile getCurrentProfile() {
    return (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }

}
