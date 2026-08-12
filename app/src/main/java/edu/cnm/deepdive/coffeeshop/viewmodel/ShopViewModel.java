package edu.cnm.deepdive.coffeeshop.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;
import edu.cnm.deepdive.coffeeshop.model.domain.Shop;
import edu.cnm.deepdive.coffeeshop.service.ShopService;
import java.util.List;
import jakarta.inject.Inject;

@HiltViewModel
public class ShopViewModel extends ViewModel {

  private final ShopService shopService;
  private final MutableLiveData<List<Shop>> shops = new MutableLiveData<>();

  @Inject
  public ShopViewModel (ShopService shopService) {
    this.shopService = shopService;
    shopService.getShops().whenComplete((updatedShops, throwable) -> {
      if (throwable == null) {
        shops.postValue(updatedShops);
      }
    });
  }

  public LiveData<List<Shop>> getShops() {
    return shops;
  }

  public void setFavorite(Shop shop, boolean isFavorite) {
    (isFavorite ? shopService.addFavorite(shop) : shopService.removeFavorite(shop))
        .whenComplete((ignored, throwable) -> {
          if (throwable != null) {
            shop.setFavorite(!isFavorite);
          }
          shops.postValue(shops.getValue());
        });
  }
}
