package edu.cnm.deepdive.coffeeshop.repository;

import edu.cnm.deepdive.coffeeshop.model.entity.Review;
import edu.cnm.deepdive.coffeeshop.model.entity.Shop;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CoffeeShopService {

  @GET("shops")
  Call<List<Shop>> getShops();

  @GET("shops/{id}")
  Call<Shop> getShopDetails(@Path("id") Long id);

  @GET("shops/{shopId}/reviews")
  Call<List<Review>> getShopReviews(@Path("shopId") Long shopId);

  @POST("shops/{shopId}/reviews")
  Call<Review> postReview(@Path("shopId") Long shopId, @Body Review review);
}
