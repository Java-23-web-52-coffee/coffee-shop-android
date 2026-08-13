package edu.cnm.deepdive.coffeeshop.controller;

import edu.cnm.deepdive.coffeeshop.controller.api.PreferenceApi;
import edu.cnm.deepdive.coffeeshop.model.dto.PreferenceDto;
import edu.cnm.deepdive.coffeeshop.model.dto.PreferenceRequestDto;
import edu.cnm.deepdive.coffeeshop.model.dto.PreferenceUpdateRequestDto;
import edu.cnm.deepdive.coffeeshop.model.entity.Preference;
import edu.cnm.deepdive.coffeeshop.service.ContextProfileService;
import edu.cnm.deepdive.coffeeshop.service.PreferenceService;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import org.springframework.core.convert.converter.Converter;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PreferenceController implements PreferenceApi {

  private final PreferenceService preferenceService;
  private final ContextProfileService contextProfileService;


  public PreferenceController(PreferenceService preferenceService,
      ContextProfileService contextProfileService){
    this.preferenceService = preferenceService;
    this.contextProfileService = contextProfileService;
  }

  @Override
  public ResponseEntity<PreferenceDto> createPreference(PreferenceRequestDto preferenceRequestDto) {
    PreferenceDto preferenceDto = preferenceService.addPreference(preferenceRequestDto,
        contextProfileService.getContextProfile().getId());
    URI location = WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(getClass()).getPreference(preferenceDto.getInterestId()))
        .toUri();
    return ResponseEntity.created(location).body(preferenceDto);
  }

  @Override
  public ResponseEntity<Void> deletePreference(UUID interestId) {
    preferenceService.deletePreference(interestId,
        contextProfileService.getContextProfile().getId());
    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<List<PreferenceDto>> listMyPreferences() {
    return ResponseEntity.ok(preferenceService.myPreferences(
        contextProfileService.getContextProfile().getId()));
  }

  @Override
  public ResponseEntity<PreferenceDto> updatePreference(UUID interestId,
      PreferenceUpdateRequestDto preferenceUpdateRequestDto) {
    return ResponseEntity.ok(preferenceService.updatePreference(interestId,
        preferenceUpdateRequestDto, contextProfileService.getContextProfile().getId()));
  }

  @GetMapping(path = PreferenceApi.PATH_DELETE_PREFERENCE, produces = MediaType.APPLICATION_JSON_VALUE)
  public PreferenceDto getPreference(@PathVariable UUID interestId) {
    return preferenceService.getPreference(interestId,
        contextProfileService.getContextProfile().getId());
  }

}
