package edu.cnm.deepdive.coffeeshop.conversion;

import edu.cnm.deepdive.coffeeshop.model.dto.RatingRequestDto;
import edu.cnm.deepdive.coffeeshop.model.entity.Rating;
import edu.cnm.deepdive.coffeeshop.repository.InterestRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class RatingRequestDtoToRatingConverter implements Converter<RatingRequestDto, Rating> {

  private final InterestRepository interestRepository;

  public RatingRequestDtoToRatingConverter(InterestRepository interestRepository) {
    this.interestRepository = interestRepository;
  }

  @Override
  public Rating convert(RatingRequestDto source) {
    return interestRepository.findById(source.getInterestId())
        .map((interest) -> {
          Rating rating = new Rating();
          rating.setValue(source.getValue());
          rating.setInterest(interest);
          return rating;
        })
        .orElseThrow();
  }


}
