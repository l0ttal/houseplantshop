package swd20.Houseplantshop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import swd20.Houseplantshop.domain.FamilyRepository;
import swd20.Houseplantshop.domain.GenusRepository;
import swd20.Houseplantshop.domain.Plant;
import swd20.Houseplantshop.domain.PlantRepository;

@Controller
public class PlantController {
	@Autowired
	private PlantRepository plantrepo;
	
	@Autowired
	private GenusRepository genusrepo;
	
	@Autowired
	private FamilyRepository familyrepo;
	
	@RequestMapping(value = {"/", "/home"})
	public String home() {
		return "home";
	}
	
	@RequestMapping(value = "/plantlist")
	public String plantList(Model model) {
		model.addAttribute("plants", plantrepo.findAll());
		return "plantlist";
	}
	
	@RequestMapping(value = "/add")
	public String addPlant(Model model) {
		model.addAttribute("plant", new Plant());
		model.addAttribute("genera", genusrepo.findAll());
		model.addAttribute("families", familyrepo.findAll());
		return "addplant";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Plant plant) {
		plantrepo.save(plant);
		return "redirect:/plantlist";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deletePlant(@PathVariable("id") Long plantId, Model model) {
		plantrepo.deleteById(plantId);
		return "redirect:/plantlist";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editPlant(@PathVariable("id") Long plantId, Model model) {
		model.addAttribute("plant", plantrepo.findById(plantId));
		model.addAttribute("genera", genusrepo.findAll());
		model.addAttribute("families", familyrepo.findAll());
		return "editplant";
	}
	
	@RequestMapping(value = "/plantcard/{id}")
	public String plantCard(@PathVariable("id") Long plantId, Model model) {
		plantrepo.findById(plantId).ifPresent(p -> model.addAttribute("plant", p));
		return "plantcard";
	}
}
