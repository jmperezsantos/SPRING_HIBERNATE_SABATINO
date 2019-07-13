package mx.ipn.cic.controlescolar.controllers;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mx.ipn.cic.controlescolar.models.RolModel;
import mx.ipn.cic.controlescolar.services.IRolService;

@Controller
@RequestMapping(path = "/rol")
public class RolController {

	private static final Log LOOGGER = LogFactory.getLog(RolController.class);

	@Autowired
	private IRolService rolService;

	// @RequestMapping(path = "/all", method = RequestMethod.POST) // esta manera es
	// igual que la anterior (lo mismo)
	@GetMapping(path = "/all")
	public ModelAndView findAll() {

		ModelAndView mav = new ModelAndView("rol/all");
		List<RolModel> roles = rolService.getAll();

		LOOGGER.info(String.format("Se encontraror %d resultados", roles.size()));
		mav.addObject("roles", roles);

		// Se regresa el html (template)
		return mav;

	}

	@GetMapping(path = "/newRolForm")
	public ModelAndView getNewRolFormThymeleaf() {

		ModelAndView mav = new ModelAndView("rol/new_rol_form");// cuando son recursos la notacion es la
																// siguiente
		// todo en minusculas y palabras ceparadas con_

		mav.addObject("rol", new RolModel());

		return mav;
	}

	@PostMapping(path = "/newRol")
	public String createNewRol(@ModelAttribute(name = "rol") RolModel rol) {

		rolService.save(rol);

		LOOGGER.info("Se guardo correctamente. " + rol.getId());

		return "redirect:/rol/all";
	}

	@GetMapping(path = "/editForm/{id}")
	public ModelAndView updateRol(@PathVariable("id") int id) {

		RolModel rol = this.rolService.getById(id);

		ModelAndView mav = new ModelAndView("rol/edit_rol_form");

		mav.addObject("rol", rol);

		return mav;
	}

	@PostMapping(path = "/updateRol")
	public String updateRol(@ModelAttribute(name = "rol") RolModel rol) {

		rolService.update(rol);

		LOOGGER.info("Se actualizó correctamente. " + rol.getId());

		return "redirect:/rol/all";
	}

	@GetMapping(path = "/delete/{id}")
	public String deleteUser(@PathVariable("id") int id) {

		RolModel user = this.rolService.getById(id);

		rolService.delete(user);

		return "redirect:/rol/all";

	}

}
