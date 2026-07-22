package edu.cnm.deepdive.coffeeshop.repository;

import edu.cnm.deepdive.coffeeshop.model.entity.Review;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, UUID> {

  Page<Review> findByShopId(UUID shopId);

  Optional<Review> findByShopIdAndProfileId(UUID shopId, UUID profileId);
}
