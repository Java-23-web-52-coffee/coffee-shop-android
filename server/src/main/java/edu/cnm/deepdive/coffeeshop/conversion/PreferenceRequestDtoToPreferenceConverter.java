package edu.cnm.deepdive.coffeeshop.conversion;

import edu.cnm.deepdive.coffeeshop.model.dto.PreferenceRequestDto;
import edu.cnm.deepdive.coffeeshop.model.entity.Preference;
import edu.cnm.deepdive.coffeeshop.repository.InterestRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class PreferenceRequestDtoToPreferenceConverter implements
    Converter<PreferenceRequestDto, Preference> {

private final InterestRepository interestRepository;

  public PreferenceRequestDtoToPreferenceConverter(InterestRepository interestRepository) {
    this.interestRepository = interestRepository;
  }

  @Override
  public Preference convert(PreferenceRequestDto source) {
    return interestRepository.findById(source.getInterestId())
        .map((interest) -> {
          Preference preference = new Preference();
          preference.setImportance(source.getImportance());
          preference.setInterest(interest);
          return preference;
        })
        .orElseThrow();
  }

}
