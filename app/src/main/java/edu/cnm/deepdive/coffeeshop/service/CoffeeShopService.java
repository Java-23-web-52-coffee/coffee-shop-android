package edu.cnm.deepdive.coffeeshop.service;

import edu.cnm.deepdive.coffeeshop.model.dto.CreateReviewDto;
import edu.cnm.deepdive.coffeeshop.model.dto.ReviewDto;
import edu.cnm.deepdive.coffeeshop.model.dto.ShopDto;
import java.util.List;
import java.util.UUID;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CoffeeShopService {

  static CoffeeShopService getInstance() {
    return null;
  }

  @GET("shops")
  Call<List<ShopDto>> getShops();

  @GET("shops/{id}")
  Call<ShopDto> getShopDetails(@Path("id") UUID id);

  @GET("shops/{shopId}/reviews")
  Call<List<ReviewDto>> getShopReviews(@Path("shopId") UUID shopId);

  @POST("shops/{shopId}/reviews")
  Call<ReviewDto> postReview(@Path("shopId") UUID shopId, @Body CreateReviewDto review);
}
