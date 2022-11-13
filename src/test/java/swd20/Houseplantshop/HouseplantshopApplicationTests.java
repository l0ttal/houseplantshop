package swd20.Houseplantshop;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import swd20.Houseplantshop.web.FamilyController;
import swd20.Houseplantshop.web.GenusController;
import swd20.Houseplantshop.web.PlantController;
import swd20.Houseplantshop.web.UserController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class HouseplantshopApplicationTests {
	
	@Autowired 
	private PlantController plantController;
	
	@Autowired
	private GenusController genusController;
	
	@Autowired
	private FamilyController familyController;
	
	@Autowired 
	private UserController userController;

	@Test
	void contextLoads() {
		assertThat(plantController).isNotNull();
		assertThat(genusController).isNotNull();
		assertThat(familyController).isNotNull();
		assertThat(userController).isNotNull();
	}
}
