package swd20.Houseplantshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import swd20.Houseplantshop.domain.Family;
import swd20.Houseplantshop.domain.FamilyRepository;
import swd20.Houseplantshop.domain.Genus;
import swd20.Houseplantshop.domain.GenusRepository;
import swd20.Houseplantshop.domain.Plant;
import swd20.Houseplantshop.domain.PlantRepository;
import swd20.Houseplantshop.domain.User;
import swd20.Houseplantshop.domain.UserRepository;

@SpringBootApplication
public class HouseplantshopApplication {
	private static final Logger log = LoggerFactory.getLogger(HouseplantshopApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HouseplantshopApplication.class, args);
	}

	@Bean
	public CommandLineRunner houseplantshop(PlantRepository plantrepo, GenusRepository genusrepo, 
			FamilyRepository familyrepo, UserRepository userrepo) {
		return (args) -> {
			log.info("save families");
			Family f1 = new Family("Moraceae", "Fig family");
			Family f2 = new Family("Urticaceae", "Nettle family");
			familyrepo.save(f1);
			familyrepo.save(f2);
			
			log.info("fetch all families");
			for (Family family : familyrepo.findAll()) {
				log.info(family.toString());
			}
			
			log.info("save genera");
			Genus g1 = new Genus("Ficus", "Fig trees", f1);
			Genus g2 = new Genus("Pilea", "", f2);
			genusrepo.save(g1);
			genusrepo.save(g2);
			
			log.info("fetch all genera");
			for (Genus genus : genusrepo.findAll()) {
				log.info(genus.toString());
			}
			
			log.info("save plants");
			Plant p1 = new Plant("Ficus Lyrata", "Fiddle-leaf fig", g1, f1);
			Plant p2 = new Plant("Pilea peperomioides", "Chinese money plant", g2, f2);
			plantrepo.save(p1);
			plantrepo.save(p2);
			
			log.info("fetch all plants");
			for (Plant plant : plantrepo.findAll()) {
				log.info(plant.toString());
			}
			
			log.info("save users");
			User u1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User u2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			userrepo.save(u1);
			userrepo.save(u2);
			
			log.info("fetch all users");
			for (User user : userrepo.findAll()) {
				log.info(user.toString());
			}
		};
	}
}
