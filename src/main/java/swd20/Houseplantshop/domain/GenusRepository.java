package swd20.Houseplantshop.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface GenusRepository extends CrudRepository<Genus, Long> {
	List<Genus> findByScientificName(String scientificName);
}
