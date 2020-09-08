package app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import app.businesslayer.UserService;
import app.dto.AbstractUser;
import app.dto.AppUser;
import app.dto.BuildingOwner;
import app.formholder.UserForm;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	ConversionService conversionService;
	public String goBack() {
		return "reservations";
	}
		
	@GetMapping("/new-user")
	public String createUser(Model model) {
		model.addAttribute("user",new UserForm());
		return "registration";
	}

	@GetMapping("/edit-user/{id}")
	public String editUser(@PathVariable long id, Model model) {
		model.addAttribute("user",conversionService.convert(userService.find(id), UserForm.class));
		return "registration";
	}
	
	@GetMapping("/delete-user/{id}")
	public String deleteUser(@PathVariable long id, Model model) {
		userService.delete(userService.find(id));
		return "reservations";
	}
	
	@RequestMapping("/save-user")
	public String save(@Valid @ModelAttribute UserForm user,BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("errorMessage","You have filled some item wrong.");
			model.addAttribute("user",user);
			return "edit-user";
		}
		AbstractUser edited =  conversionService.convert(user, AbstractUser.class);
		if(edited.getId() != 0) {
			userService.edit(edited);
		}else {
			AbstractUser u = userService.findByEmail(edited.getEmail());
			if(u!=null) {
				model.addAttribute("errorMessage","Email already exists");
				model.addAttribute("user",user);
				return "edit-user";
			}
			if(user.getIsBuildingOwner()) {
				userService.createBuildingOwner((BuildingOwner)edited);
			}else {
				userService.createAppUser((AppUser)edited);
			}
		}
		return "login";
	}
}
