package edu.cnm.deepdive.coffeeshop.service;

import edu.cnm.deepdive.coffeeshop.model.dto.PreferenceDto;
import edu.cnm.deepdive.coffeeshop.model.dto.PreferenceRequestDto;
import edu.cnm.deepdive.coffeeshop.model.dto.PreferenceUpdateRequestDto;
import edu.cnm.deepdive.coffeeshop.model.entity.Preference;
import edu.cnm.deepdive.coffeeshop.model.entity.Profile;
import edu.cnm.deepdive.coffeeshop.repository.InterestRepository;
import edu.cnm.deepdive.coffeeshop.repository.ProfileRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class PreferenceServiceImpl implements PreferenceService {

  private final ProfileRepository profileRepository;
  private final Converter<PreferenceRequestDto, Preference> inputConverter;
  private final Converter<Preference, PreferenceDto> outputConverter;

  public PreferenceServiceImpl(ProfileRepository profileRepository,
      Converter<PreferenceRequestDto, Preference> inputConverter,
      Converter<Preference, PreferenceDto> outputConverter) {
    this.profileRepository = profileRepository;
    this.inputConverter = inputConverter;
    this.outputConverter = outputConverter;
  }

  @Override
  public PreferenceDto addPreference(PreferenceRequestDto dto, UUID profileId) {
    return profileRepository.findById(profileId)
        .map(profile -> {
          Preference preference = inputConverter.convert(dto);
          preference.setProfile(profile);
          profileRepository.save(profile);
          return outputConverter.convert(preference);
        })
        .orElseThrow();

  }

  @Override
  public void deletePreference(UUID interestId, UUID profileId) {
    findAndTransform(interestId, profileId, (profile, preference) -> {
      profile.getPreferences().remove(preference);
      profileRepository.save(profile);
      return preference;
    })
        .orElseThrow();
  }

  @Override
  public List<PreferenceDto> myPreferences(UUID profileId) {
    return profileRepository.findById(profileId)
        .map(Profile::getPreferences)
        .map((prefs) -> prefs.stream().map(outputConverter::convert).toList())
        .orElseThrow();
  }

  @Override
  public PreferenceDto updatePreference(UUID interestId, PreferenceUpdateRequestDto dto,
      UUID profileId) {
    return findAndTransform(interestId, profileId, (profile, preference) -> {
      preference.setImportance(dto.getImportance());
      profileRepository.save(profile);
      return preference;
    })
        .map(outputConverter::convert)
        .orElseThrow();
  }

  @Override
  public PreferenceDto getPreference(UUID interestId, UUID profileId) {
    return findAndTransform(interestId, profileId, (_, preference) -> preference)
        .map(outputConverter::convert)
        .orElseThrow();
  }

  private Optional<Preference> findAndTransform(UUID interestId, UUID profileId,
      BiFunction<Profile, Preference, Preference> transformation) {
    return profileRepository.findById(profileId)
        .flatMap(profile -> profile.getPreferences()
            .stream()
            .filter(preference -> preference.getInterest().getId().equals(interestId))
            .findFirst()
            .map(preference -> transformation.apply(profile, preference))
        );
  }
}
