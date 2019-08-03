package mx.ipn.cic.controlescolar.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mx.ipn.cic.controlescolar.models.UserModel;
import mx.ipn.cic.controlescolar.services.IUserService;

@Controller
@RequestMapping(path = "/user")
public class UserController {

	private static final Log LOOGGER = LogFactory.getLog(RolController.class);

	@Autowired
	@Qualifier("REAL")
	private IUserService userService;

	@GetMapping(path = "/all")
	public ModelAndView findAll() {

		ModelAndView mav = new ModelAndView("users/allUsers");
		List<UserModel> users = userService.findAll();

		LOOGGER.info(String.format("Se encontraror %d resultados", users.size()));
		mav.addObject("userList", users);

		// Se regresa el html (template)
		return mav;

	}

	@GetMapping(path = "/newUserForm")
	public ModelAndView getNewUserForm() {
		ModelAndView mav = new ModelAndView("users/new_user_form");// cuando son recursos la notacion es la siguiente
																	// todo en minusculas y palabras ceparadas con_
		return mav;
	}

	@PostMapping(path = "/newUser")
	public String createNewUser(HttpServletRequest request) {

		String name = request.getParameter("name");
		String lastname = request.getParameter("lastName");
		String surname = request.getParameter("surName");
		String StrAge = request.getParameter("age");

		int age = Integer.parseInt(StrAge);

		UserModel user = new UserModel(name, lastname, surname, age);

		userService.create(user);

		LOOGGER.info("Se guardo correctamente. " + user.getId());

		return "redirect:/user/all";
	}

	// Usando anotaciones de SpringFramework
	@PostMapping(path = "/newUser2")
	public String createNewUser2(@RequestParam(name = "name") String name,
			@RequestParam(name = "lastName") String aPaterno, @RequestParam(name = "surName") String aMaterno,
			@RequestParam(name = "age", defaultValue = "300") int age) {

		UserModel user = new UserModel(name, aPaterno, aMaterno, age);

		userService.create(user);

		LOOGGER.info("Se guardo correctamente. " + user.getId());

		return "redirect:/user/all";
	}

	@PostMapping(path = "/newUser3")
	public String createNewUser3(UserModel user) {

		userService.create(user);

		LOOGGER.info("Se guardo correctamente. " + user.getId());

		return "redirect:/user/all";
	}

	@PostMapping(path = "/newUser4")
	public String createNewUser4(@Validated UserModel user) {

		userService.create(user);

		LOOGGER.info("Se guardo correctamente. " + user.getId());

		return "redirect:/user/all";
	}

	// Falta una quinta forma de hacerlo con Thymeleaft

	@GetMapping(path = "/newUserFormThymeleaf")
	public ModelAndView getNewUserFormThymeleaf() {

		ModelAndView mav = new ModelAndView("users/new_user_form_thymeleaf");// cuando son recursos la notacion es la
																				// siguiente
		// todo en minusculas y palabras ceparadas con_

		mav.addObject("user", new UserModel());

		return mav;
	}

	@PostMapping(path = "/newUser5")
	public String createNewUser5(@ModelAttribute(name = "user") UserModel user) {

		userService.create(user);

		LOOGGER.info("Se guardo correctamente. " + user.getId());

		return "redirect:/user/all";
	}

	@GetMapping(path = "/editForm/{id}")
	public ModelAndView updateUser(@PathVariable("id") String id) {

		UserModel user = this.userService.findById(id);

		ModelAndView mav = new ModelAndView("users/edit_user_form_thymeleaf");

		mav.addObject("user", user);

		return mav;
	}

	@PostMapping(path = "/updateUser")
	public String updateUser(@ModelAttribute(name = "user") UserModel user) {

		userService.update(user);

		LOOGGER.info("Se actualizó correctamente. " + user.getId());

		return "redirect:/user/all";
	}

	@GetMapping(path = "/delete/{id}")
	public String deleteUser(@PathVariable("id") String id) {

		UserModel user = this.userService.findById(id);

		userService.delete(user);

		return "redirect:/user/all";

	}

	@GetMapping(path = "/find/{name}")
	public String findByName(@PathVariable("name") String name) {

		UserModel user = this.userService.findByName(name);

		LOOGGER.info(user);

		UserModel user2 = this.userService.findByNameContaining(name);

		LOOGGER.info(user2);

		return "redirect:/user/all";

	}

}
