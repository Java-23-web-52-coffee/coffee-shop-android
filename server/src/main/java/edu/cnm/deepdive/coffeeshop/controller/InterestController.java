package edu.cnm.deepdive.coffeeshop.controller;

import edu.cnm.deepdive.coffeeshop.controller.api.InterestApi;
import edu.cnm.deepdive.coffeeshop.model.dto.InterestDto;
import edu.cnm.deepdive.coffeeshop.model.entity.Interest;
import edu.cnm.deepdive.coffeeshop.service.InterestService;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InterestController implements InterestApi {

  private final InterestService interestService;

  public InterestController(InterestService interestService) {
    this.interestService = interestService;
  }

  @Override
  public ResponseEntity<InterestDto> getInterestById(UUID id) {
    Interest interest = interestService.getInterest(id);
    InterestDto dto = interestService.toDto(interest);
    return ResponseEntity.ok(dto);
  }

  @Override
  public ResponseEntity<List<InterestDto>> listInterests() {
    List<InterestDto> interests = interestService.getAllInterests().stream()
        .map(interestService::toDto)
        .toList();
    return ResponseEntity.ok(interests);
  }

}
