package edu.cnm.deepdive.coffeeshop.conversion;

import edu.cnm.deepdive.coffeeshop.model.dto.PreferenceDto;
import edu.cnm.deepdive.coffeeshop.model.entity.Preference;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class PreferenceToPreferenceDto implements Converter<Preference, PreferenceDto> {

  @Override
  public PreferenceDto convert(Preference source) {
    PreferenceDto dto = new PreferenceDto();
    dto.setImportance(source.getImportance());
    dto.setInterestId(source.getInterest().getId());
    return dto;
  }
}
