package edu.cnm.deepdive.coffeeshop.controller;

import edu.cnm.deepdive.coffeeshop.controller.api.VisitApi;
import edu.cnm.deepdive.coffeeshop.model.dto.VisitDto;
import edu.cnm.deepdive.coffeeshop.model.dto.VisitRequestDto;
import edu.cnm.deepdive.coffeeshop.service.ContextProfileService;
import edu.cnm.deepdive.coffeeshop.service.VisitService;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VisitController implements VisitApi {

  private final VisitService visitService;
  private final ContextProfileService contextProfileService;

  public VisitController(VisitService visitService, ContextProfileService contextProfileService) {
    this.visitService = visitService;
    this.contextProfileService = contextProfileService;
  }

  @Override
  public ResponseEntity<VisitDto> createVisit(VisitRequestDto visitRequestDto) {
    return null;
  }

  @Override
  public ResponseEntity<VisitDto> getVisitById(UUID id) {
    return null;
  }

  @Override
  public ResponseEntity<List<VisitDto>> listMyVisits() {
    return null;
  }
}
