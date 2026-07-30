package edu.cnm.deepdive.coffeeshop.controller;

import edu.cnm.deepdive.coffeeshop.controller.api.ProfileApi;
import edu.cnm.deepdive.coffeeshop.model.dto.ProfileUpdateRequestDto;
import edu.cnm.deepdive.coffeeshop.model.dto.PublicProfileDto;
import edu.cnm.deepdive.coffeeshop.model.entity.Profile;
import edu.cnm.deepdive.coffeeshop.service.ProfileService;
import okhttp3.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController implements ProfileApi {

  private final ProfileService profileService;

  public ProfileController(ProfileService profileService) {
    this.profileService = profileService;
  }

  @Override
  public ResponseEntity<PublicProfileDto> getMyProfile() {
    return ResponseEntity.ok(profileService.getProfile(getCurrentProfile().getId()));
  }

  @Override
  public ResponseEntity<PublicProfileDto> updateMyProfile(
      ProfileUpdateRequestDto profileUpdateRequestDto) {
    return ResponseEntity.ok(
        profileService.updateProfile(getCurrentProfile().getId(), profileUpdateRequestDto));
  }

  private Profile getCurrentProfile() {
    return (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }

}
