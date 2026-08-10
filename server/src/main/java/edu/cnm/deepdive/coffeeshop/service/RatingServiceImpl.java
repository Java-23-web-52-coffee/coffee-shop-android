package edu.cnm.deepdive.coffeeshop.service;

import edu.cnm.deepdive.coffeeshop.model.entity.Profile;
import edu.cnm.deepdive.coffeeshop.model.entity.Rating;
import edu.cnm.deepdive.coffeeshop.repository.RatingRepository;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {

  private final RatingRepository ratingRepository;

  @Autowired
  public RatingServiceImpl(RatingRepository ratingRepository) {
    this.ratingRepository = ratingRepository;
  }

  @Override
  public Rating getRatings(Profile profile) {
    return ratingRepository.getReferenceById(profile.getId());
  }

  @Override
  public Rating saveRating(Rating rating, Profile profile) {
    return ratingRepository.save(rating);
  }

  @Override
  public List<Rating> findAllByProfile(Profile profile) {
    return ratingRepository.findAllById(Collections.singleton(profile.getId()));
  }

}
