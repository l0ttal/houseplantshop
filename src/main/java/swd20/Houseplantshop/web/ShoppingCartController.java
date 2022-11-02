package swd20.Houseplantshop.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import swd20.Houseplantshop.domain.Plant;
import swd20.Houseplantshop.domain.PlantRepository;

@Controller
public class ShoppingCartController {
	@Autowired
	private HttpSession session;

	@Autowired
	private PlantRepository plantrepo;

	// Show shopping cart
	@RequestMapping(value = "/shoppingcart")
	public String showCart(Model model) {
		model.addAttribute("cart", session.getAttribute("cart"));
		return "shoppingcart"; 
	}

	// Add to shopping cart
	@RequestMapping(value = "/addtocart/{id}", method = RequestMethod.GET)
	public String addtoCart(@PathVariable(value = "id") Long plantId) {
		@SuppressWarnings("unchecked")
		List<Plant> cart = (List<Plant>) session.getAttribute("cart");

		if (cart == null) { // ostoskoria ei ole
			cart = new ArrayList<Plant>(); // luodaan tyhjä ostoskori
			session.setAttribute("cart", cart);
		}

		Plant item = plantrepo.findById(plantId).get();
		cart.add(item);
		return "redirect:/shoppingcart";
	}
	
	// Delete from shopping cart
	@RequestMapping(value = "/deletefromcart/{id}", method = RequestMethod.GET)
	public String deleteFromCart(@PathVariable(value = "id") Long plantId) {
		@SuppressWarnings("unchecked")
		List<Plant> cart = (List<Plant>) session.getAttribute("cart");

		Plant item = plantrepo.findById(plantId).get();
		int index = cart.indexOf(item);
		cart.remove(index);
		return "redirect:/shoppingcart";
	}

	// Reset shopping chart
	@RequestMapping(value = "/resetcart", method = RequestMethod.GET)
	public String resetCart() {
		@SuppressWarnings("unchecked")
		List<Plant> cart = (List<Plant>) session.getAttribute("cart");

		if (cart != null) { // ostoskori on
			cart.clear(); // poistetaan ostoskoriin lisätyt asiat
		}
		
		return "redirect:/shoppingcart";
	}
}