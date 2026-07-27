package edu.cnm.deepdive.coffeeshop.service;

import edu.cnm.deepdive.coffeeshop.model.entity.Review;
import edu.cnm.deepdive.coffeeshop.model.entity.Shop;
import edu.cnm.deepdive.coffeeshop.repository.CoffeeShopService;
import java.util.List;
import retrofit2.Call;

public class CoffeeShopServiceImpl implements CoffeeShopService {

  @Override
  public Call<List<Shop>> getShops() {
    return null;
  }

  @Override
  public Call<Shop> getShopDetails(Long id) {
    return null;
  }

  @Override
  public Call<List<Review>> getShopReviews(Long shopId) {
    return null;
  }

  @Override
  public Call<Review> postReview(Long shopId, Review review) {
    return null;
  }
}
