package app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import app.businesslayer.BuildingService;
import app.businesslayer.ReservationService;
import app.businesslayer.RoomService;
import app.businesslayer.UserService;
import app.dto.AbstractUser;
import app.dto.Building;
import app.dto.Reservation;
import app.dto.Room;

@RestController
public class AppRestController {
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private BuildingService buildingService;
	@Autowired
	private RoomService roomService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/rest/reservations")
	public List<Reservation> getReservations(){
		return reservationService.getAllReservations();
	}
	
	@GetMapping("/rest/reservations/{id}")
	public Reservation getReservation(@PathVariable long id){
		return reservationService.find(id);
	}
	
	@GetMapping("/rest/buildings")
	public List<Building> getBuildings(){
		return buildingService.getAllBuildings();
	}
	
	@GetMapping("/rest/buildings/{id}")
	public Building getBuilding(@PathVariable long id){
		return buildingService.find(id);
	}
	
	@GetMapping("/rest/rooms")
	public List<Room> getRooms(){
		return roomService.getAllRooms();
	}
	
	@GetMapping("/rest/rooms/{id}")
	public Room getRoom(@PathVariable long id){
		return roomService.find(id);
	}
	
	@GetMapping("/rest/users")
	public List<AbstractUser> getUsers(){
		return userService.getAll();
	}
	
	@GetMapping("/rest/users/{id}")
	public AbstractUser getUser(@PathVariable long id){
		return userService.find(id);
	}

}
