package edu.cnm.deepdive.coffeeshop.repository;

import edu.cnm.deepdive.coffeeshop.model.entity.Profile;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {

  Optional<Profile> findByName(String name);
}
