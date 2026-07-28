package edu.cnm.deepdive.coffeeshop.service;

import edu.cnm.deepdive.coffeeshop.model.entity.Profile;
import edu.cnm.deepdive.coffeeshop.model.entity.Visit;
import java.util.UUID;

public interface VisitService {

  Visit saveVisit(Visit visit, Profile profile);

  Visit getVisit(UUID id);

  Visit getShopVisit(UUID shopId, Profile profile);
}
