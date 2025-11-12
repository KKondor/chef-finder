package hu.unideb.repository;

import hu.unideb.model.Chef;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChefRepository extends CrudRepository<Chef,Long> {
   List<Chef> findAll();
}
