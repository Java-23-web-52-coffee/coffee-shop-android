package edu.cnm.deepdive.coffeeshop.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;
import edu.cnm.deepdive.coffeeshop.model.domain.Shop;
import edu.cnm.deepdive.coffeeshop.service.ShopService;
import java.util.List;
import java.util.UUID;
import jakarta.inject.Inject;

@HiltViewModel
public class ShopViewModel extends ViewModel {

  private final ShopService shopService;
  private final LiveData<List<Shop>> shops;

  @Inject
  public ShopViewModel (ShopService shopService) {
    this.shopService = shopService;
    shops = new MutableLiveData<>(buildTestShops()); // FIXME: 8/11/26 Replace with actual data from repository.
  }

  public LiveData<List<Shop>> getShops() {
    return shops;
  }

  private static List<Shop> buildTestShops() {
    return List.of(
        new Shop(
            UUID.randomUUID(),
            "Average coffee shop",
            null, null, null, null, null, null,
            true
        ) // TODO: 8/11/26 Add more test shops.
    );
  }

}
