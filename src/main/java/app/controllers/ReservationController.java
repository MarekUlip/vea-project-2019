package app.controllers;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import app.businesslayer.ReservationService;
import app.businesslayer.RoomService;
import app.businesslayer.UserService;
import app.dto.AbstractUser;
import app.dto.Reservation;
import app.formholder.ReservationForm;

@Controller
public class ReservationController {
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private RoomService roomService;
	@Autowired 
	private UserService userService;
	@Autowired
	ConversionService conversionService;
	//private Date from = new Date(System.currentTimeMillis());
	//private Date to = new Date(System.currentTimeMillis()+3600000);
	
	public String goBack() {
		return "reservations";
	}
	
	public String getDateText(long time) {
		return new Date(time).toString();
	}

	@GetMapping(path = {"/reservations", "/index","/"})
	public String getAllReservations(Model model){
		model.addAttribute("reservationsList",getConvertedList(reservationService.getAllReservations()));//reservationService.getAllReservations());
		return "reservations";
	}
	
	
	@GetMapping("/reservations/{id}")
	public String getAllReservationsFromRoom(@PathVariable("id") long id, Model model){
		/*conversionService.convert(reservationService.getAllReservationsFromRoom(roomService.find(id)),
				TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Reservation.class)),
				TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(ReservationForm.class)))*/
		model.addAttribute("reservationsList",getConvertedList(reservationService.getAllReservationsFromRoom(roomService.find(id))));//reservationService.getAllReservationsFromRoom(roomService.find(id)));
		return "reservations";
	}
	
	@GetMapping("/new-reservation")
	public String createReservation(Model model) {
		ReservationForm form = new ReservationForm();
		form.setTimeFrom(new Date(System.currentTimeMillis()));
		form.setTimeTo(new Date(System.currentTimeMillis()+3600000));
		model.addAttribute("reservation", form);
		return "edit-reservation";
	}
	
	public String search() {
		return "";
	}
	
	@GetMapping("/edit-reservation/{id}")
	public String editReservation(@PathVariable("id") long id, Model model) {
		model.addAttribute("reservation", conversionService.convert(reservationService.find(id),ReservationForm.class));
		return "edit-reservation";
	}
	
	@GetMapping("/delete-reservation/{id}")
	public String deleteReservation(@PathVariable("id") long id, Model model) {
		reservationService.delete(reservationService.find(id));
		model.addAttribute("reservationsList",getConvertedList(reservationService.getAllReservations()));
		return "reservations";
	}
	
	private List<ReservationForm> getConvertedList(List<Reservation> reservations){
		List<ReservationForm> rList = (List<ReservationForm>)conversionService.convert(reservations,
				TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Reservation.class)),
				TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(ReservationForm.class)));
		addIsEditableAttribute(rList);
		return rList;
	}
	
	
	@RequestMapping("/save-reservation")
	public String save(@Valid @ModelAttribute ReservationForm form,BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("errorMessage","Wrong date format.");
			model.addAttribute("reservation",form);
			return "edit-reservation";
		}
		Reservation reservation = conversionService.convert(form, Reservation.class);
		String email = userService.getLoggedUserEmail();
		if((reservation.getFrom()<reservation.getTo()) && isTimePossible(reservation)) {
			reservation.setOwner(userService.findByEmail(email));
			if(reservation.getId() != 0) {
				reservationService.edit(reservation);
			}else {
				reservationService.create(reservation);
			}
			model.addAttribute("reservationsList",getConvertedList(reservationService.getAllReservations()));
			return "reservations";
		}
		else {
			model.addAttribute("errorMessage","Reservation already exist in selected time or time is impossible to set.");
			model.addAttribute("reservation",conversionService.convert(reservation, ReservationForm.class));
			return "edit-reservation";
		}
	}
	
	private boolean isTimePossible(Reservation reservation) {
		List<Reservation> reservations =reservationService.getReservationsBetweenFromRoom(reservation.getFrom(), reservation.getTo(),reservation.getRoom());
		if (!reservations.isEmpty()) {
			if (reservations.size()>1) {
				return false;
			}
			if(reservations.get(0).getId()==reservation.getId()) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}
		
	
	public void addIsEditableAttribute(List<ReservationForm> items) {
		long userId = userService.getLoggedUser().getId();
		for(ReservationForm form: items) {
			form.setIsEditable(userId == form.getOwner().getId());
		}
	}
	
	public boolean isEditable(long id) {
		String email = userService.getLoggedUserEmail();
		if(email == null) {
			return false;
		}
		return reservationService.find(id).getOwner().getId() == userService.findByEmail(email).getId();
	}

}
