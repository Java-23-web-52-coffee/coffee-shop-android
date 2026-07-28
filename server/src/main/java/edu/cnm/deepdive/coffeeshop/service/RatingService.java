package edu.cnm.deepdive.coffeeshop.service;

import edu.cnm.deepdive.coffeeshop.model.entity.Profile;
import edu.cnm.deepdive.coffeeshop.model.entity.Rating;
import java.util.List;

public interface RatingService {

  Rating getRatings(Profile profile);

  Rating saveRating(Rating rating, Profile profile);

  List<Rating> findAllByProfile(Profile profile);

}
