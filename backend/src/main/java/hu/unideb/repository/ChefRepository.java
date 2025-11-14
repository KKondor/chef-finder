package hu.unideb.repository;

import hu.unideb.model.Chef;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChefRepository extends CrudRepository<Chef,Long> {
    List<Chef> findByNameContainingIgnoreCaseOrSpecialtyContainingIgnoreCase(String name, String specialty);    boolean existsByRestaurantId(Long restaurantId);
    List<Chef> findAll();
}
