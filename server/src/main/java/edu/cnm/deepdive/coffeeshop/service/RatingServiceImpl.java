package edu.cnm.deepdive.coffeeshop.service;

import edu.cnm.deepdive.coffeeshop.model.dto.RatingDto;
import edu.cnm.deepdive.coffeeshop.model.dto.RatingRequestDto;
import edu.cnm.deepdive.coffeeshop.model.dto.RatingUpdateRequestDto;
import edu.cnm.deepdive.coffeeshop.model.entity.Interest;
import edu.cnm.deepdive.coffeeshop.model.entity.Profile;
import edu.cnm.deepdive.coffeeshop.model.entity.Rating;
import edu.cnm.deepdive.coffeeshop.repository.InterestRepository;
import edu.cnm.deepdive.coffeeshop.repository.VisitRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {

  private final VisitRepository visitRepository;
  private final InterestRepository interestRepository;
  private final Converter<RatingRequestDto, Rating> inputConverter;
  private final Converter<Rating, RatingDto> outputConverter;

  public RatingServiceImpl(VisitRepository visitRepository, InterestRepository interestRepository,
      Converter<RatingRequestDto, Rating> inputConverter,
      Converter<Rating, RatingDto> outputConverter) {
    this.visitRepository = visitRepository;
    this.interestRepository = interestRepository;
    this.outputConverter = outputConverter;
    this.inputConverter = inputConverter;
  }

  @Override
  public List<RatingDto> listRatings(UUID visitId) {
    return interestRepository.findById(visitId)
        .map(visit -> visit.getRatings().stream()
            .map(outputConverter::convert)
            .toList())
        .orElse(List.of());
  }

  @Override
  public void deleteRating(UUID visitId, UUID interest, Profile profile) {
    interestRepository.findById(visitId)
        .map(Interest::getRatings)
        .map(
            (ratings) -> ratings.removeIf(rating ->
                rating.getInterest().getId().equals(interest)));
  }

  @Override
  public RatingDto saveRating(UUID visitId, RatingRequestDto ratingRequestDto, Profile profile) {
    return visitRepository.findById(visitId)
        .map(visit -> {
          Rating rating = inputConverter.convert(ratingRequestDto);
          rating.setVisit(visit);
          visit.getRatings().add(rating);
          visitRepository.save(visit);
          return outputConverter.convert(rating);
        })
        .orElseThrow();
  }

  @Override
  public RatingDto updateRating(UUID visitId, UUID interestId,
      RatingUpdateRequestDto ratingUpdateRequestDto, Profile profile) {
    return visitRepository.findById(visitId)
        .flatMap(visit -> visit.getRatings().stream()
            .filter(rating -> rating.getInterest().getId().equals(interestId))
            .findFirst()
            .map(rating -> {
              rating.setValue(ratingUpdateRequestDto.getValue());
              visitRepository.save(visit);
              return outputConverter.convert(rating);
            })
           )
        .orElseThrow();
  }

  @Override
  public RatingDto getRating(UUID visitId, UUID interestId) {
    return visitRepository.findById(visitId)
        .flatMap(visit -> visit.getRatings().stream()
            .filter(rating -> rating.getInterest().getId().equals(interestId))
            .findFirst()
            .map(outputConverter::convert)
        )
        .orElseThrow();
  }

}
