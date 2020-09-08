package app.converters;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import app.dto.Reservation;
import app.formholder.ReservationForm;

@Component
public class ReservationToReservationFormConverter implements Converter<Reservation, ReservationForm>{

	@Override
	public ReservationForm convert(Reservation source) {
		ReservationForm form = new ReservationForm();
		form.setId(source.getId());
		form.setOwner(source.getOwner());
		form.setRoom(source.getRoom());
		form.setTimeFrom(new Date(source.getFrom()));
		form.setTimeTo(new Date(source.getTo()));
		return form;
	}
	
}
