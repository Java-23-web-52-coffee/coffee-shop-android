package edu.cnm.deepdive.coffeeshop.repository;

import edu.cnm.deepdive.coffeeshop.model.entity.Favorite;
import edu.cnm.deepdive.coffeeshop.model.entity.FavoriteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteId> {

}
