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
import app.businesslayer.RoomService;
import app.dto.Room;

@Controller
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	@Autowired
	private BuildingService buildingService;
	
	@GetMapping("/list-rooms")
	public String getAllRooms(Model model){
		model.addAttribute("roomList",roomService.getAllRooms());
		return "list-rooms";
	}
	
	@GetMapping("/new-room")
	public String createRoom(Model model) {
		model.addAttribute("room",new Room());
		return "edit-room";
	}
	
	@GetMapping("/edit-room/{id}")
	public String editRoom(@PathVariable("id") long id, Model model) {
		model.addAttribute("room",roomService.find(id));
		return "edit-room";
	}
	
	@GetMapping("/delete-room/{id}")
	public String deleteRoom(@PathVariable("id") long id, Model model) {
		roomService.delete(roomService.find(id));
		return "reservations";
	}

	@RequestMapping("/save-room")
	public String save(@Valid @ModelAttribute Room room,BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("errorMessage","You have filled some item wrong.");
			model.addAttribute("room",room);
			return "edit-room";
		}
		if(room.getId() != 0) {
			roomService.edit(room);
		}else {
			roomService.create(room);
		}
		return "redirect:/reservations";
	}
}
