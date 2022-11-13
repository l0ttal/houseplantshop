package swd20.Houseplantshop;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import swd20.Houseplantshop.domain.Family;
import swd20.Houseplantshop.domain.FamilyRepository;
import swd20.Houseplantshop.domain.Genus;
import swd20.Houseplantshop.domain.GenusRepository;
import swd20.Houseplantshop.domain.Plant;
import swd20.Houseplantshop.domain.PlantRepository;
import swd20.Houseplantshop.domain.User;
import swd20.Houseplantshop.domain.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PlantRepositoryTest {
	
	@Autowired 
	private PlantRepository plantrepo;
	
	@Autowired
	private GenusRepository genusrepo;
	
	@Autowired
	private FamilyRepository familyrepo;
	
	@Autowired 
	private UserRepository userrepo;
	
	@Test
	public void findByScientificNameShouldReturnPlant() {
		List<Plant> plants = plantrepo.findByScientificName("Ficus Lyrata");
		
		assertThat(plants).hasSize(1);
		assertThat(plants.get(0).getCommonName()).isEqualTo("Fiddle-leaf fig");
	}
	
	@Test
	public void findByScientificNameShouldReturnGenus() {
		List<Genus> genera = genusrepo.findByScientificName("Ficus");
		
		assertThat(genera).hasSize(1);
		assertThat(genera.get(0).getCommonName()).isEqualTo("Fig trees");
	}
	
	@Test 
	public void findByScientificNameShouldReturnFamily() {
		List<Family> families = familyrepo.findByScientificName("Moraceae");
		
		assertThat(families).hasSize(1);
		assertThat(families.get(0).getCommonName()).isEqualTo("Fig family");
	}
	
	@Test
	public void findByRoleShouldReturnUser() {
		List<User> users = userrepo.findByRole("ADMIN");
		
		assertThat(users).hasSize(1);
		assertThat(users.get(0).getUsername()).isEqualTo("admin");
	}

	@Test
	public void createNewPlant() {
		Plant plant = new Plant("Ficus elastica", "Rubber plant", "img", "© photo credit", 15.00, genusrepo.findByScientificName("Ficus").get(0), familyrepo.findByScientificName("Moraceae").get(0));
		plantrepo.save(plant);
		assertThat(plant.getId()).isNotNull();
	}
	
	@Test
	public void deletePlant() {
		List<Plant> plants = plantrepo.findByScientificName("Ficus Lyrata");
		plantrepo.deleteById(plants.get(0).getId());
		
		assertThat(plants).hasSize(1);
	}
	
	@Test
	public void createNewUser() {
		User user = new User("testi", "testi", "TESTI");
		userrepo.save(user);
		assertThat(user.getId()).isNotNull();
	}
	
	@Test
	public void deleteUser() {
		List<User> users = userrepo.findByRole("USER");
		userrepo.deleteById(users.get(0).getId());
		
		assertThat(users).hasSize(1);
	}
}
