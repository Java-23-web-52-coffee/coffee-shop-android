package edu.cnm.deepdive.coffeeshop.controller;

import edu.cnm.deepdive.coffeeshop.controller.api.ProfileApi;
import edu.cnm.deepdive.coffeeshop.model.dto.ProfileUpdateRequestDto;
import edu.cnm.deepdive.coffeeshop.model.dto.PublicProfileDto;
import org.springframework.http.ResponseEntity;

public class ProfileController implements ProfileApi {

  @Override
  public ResponseEntity<PublicProfileDto> getMyProfile() {
    return null;
  }

  @Override
  public ResponseEntity<PublicProfileDto> updateMyProfile(
      ProfileUpdateRequestDto profileUpdateRequestDto) {
    return null;
  }

}
