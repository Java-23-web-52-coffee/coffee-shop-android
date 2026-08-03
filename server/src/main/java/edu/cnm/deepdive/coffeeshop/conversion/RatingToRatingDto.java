package edu.cnm.deepdive.coffeeshop.conversion;

import edu.cnm.deepdive.coffeeshop.model.dto.RatingDto;
import edu.cnm.deepdive.coffeeshop.model.entity.Rating;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class RatingToRatingDto implements Converter<Rating, RatingDto> {

  @Override
  public RatingDto convert(Rating source) {
    RatingDto dto = new RatingDto();
    dto.setValue(source.getValue());
    dto.setVisitId(source.getVisit().getId());
    dto.setInterestId(source.getInterest().getId());
    return dto;
  }

}
