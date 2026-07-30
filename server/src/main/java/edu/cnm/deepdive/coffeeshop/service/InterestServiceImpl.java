package edu.cnm.deepdive.coffeeshop.service;

import edu.cnm.deepdive.coffeeshop.model.entity.Interest;
import edu.cnm.deepdive.coffeeshop.model.entity.Preference;
import edu.cnm.deepdive.coffeeshop.model.entity.Profile;
import edu.cnm.deepdive.coffeeshop.repository.InterestRepository;
import edu.cnm.deepdive.coffeeshop.repository.ProfileRepository;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterestServiceImpl implements InterestService {

  private final InterestRepository interestRepository;
  private final ProfileRepository profileRepository;

  @Autowired
  public InterestServiceImpl (InterestRepository interestRepository,
      ProfileRepository profileRepository) {
    this.interestRepository = interestRepository;
    this.profileRepository = profileRepository;
  }

  @Override
  public Interest getInterests(Interest interest) {
    return null;
  }

  @Override
  public List<Interest> getAllInterests(Profile profile) {
    return List.of();
  }

  @Override
  public Interest preferredInterest(Profile profile) {
    return null;
  }
}
