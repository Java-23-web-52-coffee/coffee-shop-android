package edu.cnm.deepdive.coffeeshop.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;
import edu.cnm.deepdive.coffeeshop.R;
import edu.cnm.deepdive.coffeeshop.model.domain.Shop;
import edu.cnm.deepdive.coffeeshop.service.ShopService;
import edu.cnm.deepdive.coffeeshop.service.VisitService;
import java.util.List;
import java.util.UUID;
import jakarta.inject.Inject;

@HiltViewModel
public class ShopViewModel extends ViewModel {

  private final ShopService shopService;
  private final MutableLiveData<List<Shop>> shops;

  @Inject
  public ShopViewModel (ShopService shopService, VisitService visitService) {
    this.shopService = shopService;
    shops = new MutableLiveData<>();
    fetchAllShops();
  }

  public LiveData<List<Shop>> getShops() {
    return shops;
  }

  public void fetchAllShops() {
    shopService.getShops()
        .thenAccept(shops::postValue);
  }

  private static List<Shop> buildTestShops() {
    return List.of(
        new Shop(
            UUID.randomUUID(),
            "Average coffee shop",
            null, null, null, null, null, null,
            true

        ), new Shop(
            UUID.randomUUID(),
            "Espresso Express",
            null, null, null, null, null, null,
            false
        ),
        new Shop(
            UUID.randomUUID(),
            "Bean & Brew",
            null, null, null, null, null, null,
            true
        ),
        new Shop(
            UUID.randomUUID(),
            "Roast & Co.",
            null, null, null, null, null, null,
            false
        ),
        new Shop(
            UUID.randomUUID(),
            "The Daily Grind",
            null, null, null, null, null, null,
            true
        )
    );
  }

  public void setFavorite(Shop shop, boolean isFavorite) {

  }
}
