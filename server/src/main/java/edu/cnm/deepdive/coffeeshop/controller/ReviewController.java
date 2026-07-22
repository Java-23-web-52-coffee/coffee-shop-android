package edu.cnm.deepdive.coffeeshop.controller;


import edu.cnm.deepdive.coffeeshop.model.entity.Review;
import edu.cnm.deepdive.coffeeshop.repository.ReviewRepository;
import edu.cnm.deepdive.coffeeshop.repository.ShopRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shops/{shopId}/reviews")
public class ReviewController {

  private final ReviewRepository reviewRepository;
  private final ShopRepository shopRepository;

  public ReviewController(ReviewRepository reviewRepository, ShopRepository shopRepository) {
    this.reviewRepository = reviewRepository;
    this.shopRepository = shopRepository;
  }

  @GetMapping
  public List<Review> getReviewsForShop(@PathVariable UUID shopId) {
    return (List<Review>) reviewRepository.findByShopId(shopId);
  }

  @PostMapping
  public Review addReview(@PathVariable UUID shopId, @RequestBody Review review) {
    return shopRepository.findById(shopId)
        .map(shop -> {
          review.setShop(shop);
          return reviewRepository.save(review);
        })
        .orElseThrow();
  }
}
