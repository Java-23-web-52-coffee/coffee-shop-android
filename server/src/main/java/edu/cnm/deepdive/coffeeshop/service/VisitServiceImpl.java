package edu.cnm.deepdive.coffeeshop.service;

import edu.cnm.deepdive.coffeeshop.model.entity.Profile;
import edu.cnm.deepdive.coffeeshop.model.entity.Visit;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitServiceImpl implements VisitService {

  private final VisitService service;

  @Autowired
  public VisitServiceImpl(VisitService service) {
    this.service = service;
  }

  @Override
  public Visit saveVisit(Visit visit, Profile profile) {
    return service.saveVisit(visit, profile);
  }

  @Override
  public Visit getVisit(UUID id) {
    return service.getVisit(id);
  }

  @Override
  public Visit getShopVisit(UUID shopId, Profile profile) {
    return service.getShopVisit(shopId, profile);
  }

}
