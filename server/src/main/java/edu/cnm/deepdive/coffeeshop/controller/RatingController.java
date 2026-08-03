package edu.cnm.deepdive.coffeeshop.controller;

import edu.cnm.deepdive.coffeeshop.controller.api.RatingApi;
import edu.cnm.deepdive.coffeeshop.model.dto.RatingDto;
import edu.cnm.deepdive.coffeeshop.model.dto.RatingRequestDto;
import edu.cnm.deepdive.coffeeshop.model.dto.RatingUpdateRequestDto;
import edu.cnm.deepdive.coffeeshop.service.ContextProfileService;
import edu.cnm.deepdive.coffeeshop.service.RatingService;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import org.springframework.context.annotation.Profile;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("service")
public class RatingController implements RatingApi {

  private final RatingService ratingService;
  private final ContextProfileService contextProfileService;

  public RatingController(RatingService ratingService,
      ContextProfileService contextProfileService) {
    this.ratingService = ratingService;
    this.contextProfileService = contextProfileService;
  }

  @Override
  public ResponseEntity<RatingDto> createRating(UUID visitId, RatingRequestDto ratingRequestDto) {
    RatingDto dto = ratingService.saveRating(visitId, ratingRequestDto,
        contextProfileService.getContextProfile());
    URI location = WebMvcLinkBuilder.linkTo(
        WebMvcLinkBuilder.methodOn(getClass()).getRating(visitId, dto.getInterestId())).toUri();
    return ResponseEntity.created(location).body(dto);
  }

  @Override
  public ResponseEntity<Void> deleteRating(UUID visitId, UUID interestId) {
    ratingService.deleteRating(visitId, interestId, contextProfileService.getContextProfile());
    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<List<RatingDto>> listRatings(UUID visitId) {
    return ResponseEntity.ok(ratingService.listRatings(visitId));
  }

  @Override
  public ResponseEntity<RatingDto> updateRating(UUID visitId, UUID interestId,
      RatingUpdateRequestDto ratingUpdateRequestDto) {
    return ResponseEntity.ok(ratingService.updateRating(visitId, interestId, ratingUpdateRequestDto,
        contextProfileService.getContextProfile()));
  }

  @GetMapping(path = PATH_DELETE_RATING, produces = MediaType.APPLICATION_JSON_VALUE)
  RatingDto getRating(@PathVariable UUID visitId, @PathVariable UUID interestId) {
    return ratingService.getRating(visitId, interestId);
  }

}
