package swd20.Houseplantshop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import swd20.Houseplantshop.domain.Family;
import swd20.Houseplantshop.domain.FamilyRepository;

@CrossOrigin
@Controller
public class FamilyController {
	@Autowired
	private FamilyRepository familyrepo;
	
	@RequestMapping(value = "/familylist")
	public String familyList(Model model) {
		model.addAttribute("families", familyrepo.findAll());
		return "familylist";
	}
	
	@RequestMapping(value = "/addfamily") 
	public String addFamily(Model model) {
		model.addAttribute("family", new Family());
		return "addfamily";
	}
	
	@RequestMapping(value = "/savefamily", method = RequestMethod.POST)
	public String saveFamily(Family family) {
		familyrepo.save(family);
		return "redirect:/familylist";
	}
	
	@RequestMapping(value = "/deletefamily/{familyid}", method = RequestMethod.GET)
	public String deleteFamily(@PathVariable("familyid") Long familyId, Model model) {
		familyrepo.deleteById(familyId);
		return "redirect:/familylist";
	}
}
