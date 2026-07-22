package edu.cnm.deepdive.coffeeshop.controller;
import edu.cnm.deepdive.coffeeshop.model.entity.Review;
import edu.cnm.deepdive.coffeeshop.repository.ProfileRepository;
import edu.cnm.deepdive.coffeeshop.repository.ReviewRepository;
import edu.cnm.deepdive.coffeeshop.repository.ShopRepository;
import java.security.Principal;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/shops/{shopId}/reviews")
public class ReviewController {

  private final ReviewRepository reviewRepository;
  private final ShopRepository shopRepository;
  private final ProfileRepository profileRepository;

  public ReviewController(ReviewRepository reviewRepository, ShopRepository shopRepository,
      ProfileRepository profileRepository) {
    this.reviewRepository = reviewRepository;
    this.shopRepository = shopRepository;
    this.profileRepository = profileRepository;
  }

  @GetMapping
  public List<Review> getReviewsForShop(@PathVariable UUID shopId) {
    return reviewRepository.findByShopId(shopId);
  }

  @PostMapping
  public Review addReview(@PathVariable UUID shopId, @RequestBody Review review,
      Principal principal) {
    var shop = shopRepository.findById(shopId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Shop not found"));
    var profile = profileRepository.findByEmail(principal.getName())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED,
            "Authenticated profile not found"));
    review.setShop(shop);
    review.setProfile(profile);
    return reviewRepository.save(review);
  }
}
