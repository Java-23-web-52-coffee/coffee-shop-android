package edu.cnm.deepdive.coffeeshop.service;

import edu.cnm.deepdive.coffeeshop.model.entity.Interest;
import edu.cnm.deepdive.coffeeshop.model.entity.Profile;
import edu.cnm.deepdive.coffeeshop.repository.InterestRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterestServiceImpl implements InterestService {

  private final InterestService service;

  @Autowired
  public InterestServiceImpl(InterestService service) {
    this.service = service;
  }

  @Override
  public Interest getInterests(Interest interest) {
    return service.getInterests(interest);
  }

  @Override
  public List<Interest> getAllInterests(Profile profile) {
    return service.getAllInterests(profile);
  }

  @Override
  public Interest preferredInterest(Profile profile) {
    return service. preferredInterest(profile);
  }

}
