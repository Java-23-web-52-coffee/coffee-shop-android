package edu.cnm.deepdive.coffeeshop.service;

import edu.cnm.deepdive.coffeeshop.model.entity.Profile;
import edu.cnm.deepdive.coffeeshop.model.entity.Rating;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {

  private final RatingService service;

  @Autowired
  public RatingServiceImpl(RatingService service) {
    this.service = service;
  }

  @Override
  public Rating getRatings(Profile profile) {
    return service.getRatings(profile);
  }

  @Override
  public Rating saveRating(Rating rating, Profile profile) {
    return service.saveRating(rating, profile);
  }

  @Override
  public List<Rating> findAllByProfile(Profile profile) {
    return service.findAllByProfile(profile);
  }

}
