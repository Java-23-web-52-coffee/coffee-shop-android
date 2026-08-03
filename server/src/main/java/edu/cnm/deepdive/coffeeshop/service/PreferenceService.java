package edu.cnm.deepdive.coffeeshop.service;

import edu.cnm.deepdive.coffeeshop.model.dto.PreferenceDto;
import edu.cnm.deepdive.coffeeshop.model.dto.PreferenceRequestDto;
import edu.cnm.deepdive.coffeeshop.model.dto.PreferenceUpdateRequestDto;
import edu.cnm.deepdive.coffeeshop.model.entity.Preference;
import java.util.List;
import java.util.UUID;

public interface PreferenceService {

  PreferenceDto addPreference(PreferenceRequestDto dto, UUID profileId);

  void deletePreference(UUID interestId, UUID profileId);

  List<PreferenceDto> myPreferences(UUID profileId);

  PreferenceDto updatePreference(UUID interestId, PreferenceUpdateRequestDto dto, UUID profileId);

  PreferenceDto getPreference(UUID interestId, UUID profileId);

}
