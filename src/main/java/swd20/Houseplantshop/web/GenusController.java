package swd20.Houseplantshop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import swd20.Houseplantshop.domain.FamilyRepository;
import swd20.Houseplantshop.domain.Genus;
import swd20.Houseplantshop.domain.GenusRepository;

@CrossOrigin
@Controller
public class GenusController {
	@Autowired
	private GenusRepository genusrepo;
	
	@Autowired
	private FamilyRepository familyrepo;
	
	@RequestMapping(value = "/genuslist")
	public String genusList(Model model) {
		model.addAttribute("genera", genusrepo.findAll());
		return "genuslist";
	}
	
	@RequestMapping(value = "/addgenus")
	public String addGenus(Model model) {
		model.addAttribute("genus", new Genus());
		model.addAttribute("families", familyrepo.findAll());
		return "addgenus";
	}
	
	@RequestMapping(value = "/savegenus", method = RequestMethod.POST)
	public String saveGenus(Genus genus) {
		genusrepo.save(genus);
		return "redirect:/genuslist";
	}
	
	@RequestMapping(value = "/deletegenus/{genusid}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteGenus(@PathVariable("genusid") Long genusId, Model model) {
		genusrepo.deleteById(genusId);
		return "redirect:/genuslist";
	}
}
