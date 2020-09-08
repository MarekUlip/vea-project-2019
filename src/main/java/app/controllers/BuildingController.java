package app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import app.businesslayer.BuildingService;
import app.dto.Building;

@Controller
public class BuildingController {
	
	@Autowired
	private BuildingService buildingService;
	
	@GetMapping("/list-buildings")
	public String getAllBuildings(Model model){
		model.addAttribute("buildingList",buildingService.getAllBuildings());
		return "building-list";
	}

	@GetMapping("/new-building")
	public String createBuilding(Model model) {
		model.addAttribute("building",new Building());
		return "edit-building";
	}
	
	@GetMapping("/edit-building/{id}")
	public String editBuilding(@PathVariable("id") long id, Model model) {
		model.addAttribute("building",buildingService.find(id));
		return "edit-building";
	}
	
	@GetMapping("/delete-building/{id}")
	public String deleteBuilding(@PathVariable("id") long id, Model model) {
		buildingService.delete(buildingService.find(id));
		model.addAttribute("buildingList",buildingService.getAllBuildings());
		return "building-list";
	}
	
	@RequestMapping("/save-building")
	public String save(@Valid @ModelAttribute Building building, BindingResult bindingResult,Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("errorMessage","You have filled some item wrong.");
			model.addAttribute("building",building);
			return "edit-building";
		}
		
		
		if(building.getId() != 0) {
			buildingService.edit(building);
		}else {
			buildingService.create(building);
		}
		return "reservations";
	}
}
