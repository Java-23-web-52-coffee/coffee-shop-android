package edu.cnm.deepdive.coffeeshop.service;

import edu.cnm.deepdive.coffeeshop.model.dto.CreateReviewDto;
import edu.cnm.deepdive.coffeeshop.model.dto.ReviewDto;
import edu.cnm.deepdive.coffeeshop.model.dto.ShopDto;
import java.util.List;
import java.util.UUID;
import retrofit2.Call;

public class CoffeeShopServiceImpl implements CoffeeShopService {

  @Override
  public Call<List<ShopDto>> getShops() {
    return null;
  }

  @Override
  public Call<ShopDto> getShopDetails(UUID id) {
    return null;
  }

  @Override
  public Call<List<ReviewDto>> getShopReviews(UUID shopId) {
    return null;
  }

  @Override
  public Call<ReviewDto> postReview(UUID shopId, CreateReviewDto review) {
    return null;
  }
}
