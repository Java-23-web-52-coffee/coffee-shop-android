package edu.cnm.deepdive.coffeeshop.service;

import edu.cnm.deepdive.coffeeshop.model.dto.RatingDto;
import edu.cnm.deepdive.coffeeshop.model.dto.RatingRequestDto;
import edu.cnm.deepdive.coffeeshop.model.dto.RatingUpdateRequestDto;
import edu.cnm.deepdive.coffeeshop.model.entity.Profile;
import edu.cnm.deepdive.coffeeshop.model.entity.Rating;
import java.util.List;
import java.util.UUID;

public interface RatingService {

  List<RatingDto> listRatings(UUID visitId);

  void deleteRating(UUID visitId, UUID interest, Profile profile);

  RatingDto saveRating(UUID visitId, RatingRequestDto ratingRequestDto, Profile profile);

  RatingDto updateRating(UUID visitId, UUID interestId, RatingUpdateRequestDto ratingUpdateRequestDto, Profile profile);

  RatingDto getRating(UUID visitId, UUID interestId);

}