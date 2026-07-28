package edu.cnm.deepdive.coffeeshop.service;

import edu.cnm.deepdive.coffeeshop.model.entity.Interest;
import edu.cnm.deepdive.coffeeshop.model.entity.Profile;
import java.util.List;

public interface InterestService {

  Interest getInterests(Interest interest);

  List<Interest> getAllInterests(Profile profile);

  Interest preferredInterest(Profile profile);
}
