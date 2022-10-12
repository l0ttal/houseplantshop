package swd20.Houseplantshop.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface FamilyRepository extends CrudRepository<Family, Long> {
	List<Family> findByScientificName(String scientificName);
}
