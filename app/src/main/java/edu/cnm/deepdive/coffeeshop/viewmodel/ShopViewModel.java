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
    refreshShops();
  }

  public LiveData<List<Shop>> getShops() {
    return shops;
  }

  public void setFavorite(Shop shop, boolean isFavorite) {
    (isFavorite ? shopService.addFavorite(shop) : shopService.removeFavorite(shop))
        .whenComplete((result, throwable) -> {
          if (throwable == null) {
            refreshShops();
          }
        });
  }

  private void refreshShops() {
    shopService.getShops().whenComplete((shopList, throwable) -> {
      if (throwable == null) {
        shops.postValue(shopList);
      }
    });
  }

}
