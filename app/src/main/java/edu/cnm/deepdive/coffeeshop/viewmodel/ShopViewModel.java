package edu.cnm.deepdive.coffeeshop.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;
import edu.cnm.deepdive.coffeeshop.R;
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
            true, R.drawable.coffee
        ), new Shop(
            UUID.randomUUID(),
            "Espresso Express",
            null, null, null, null, null, null,
            false, R.drawable.coffee_shop
        ),
        new Shop(
            UUID.randomUUID(),
            "Bean & Brew",
            null, null, null, null, null, null,
            true, R.drawable.coffee_shop_3
        ),
        new Shop(
            UUID.randomUUID(),
            "Roast & Co.",
            null, null, null, null, null, null,
            false, R.drawable.coffee_shop_4
        ),
        new Shop(
            UUID.randomUUID(),
            "The Daily Grind",
            null, null, null, null, null, null,
            true, R.drawable.coffee_shop
        )
    );
  }

  public void setFavorite(Shop shop, boolean isFavorite) {

  }
}
