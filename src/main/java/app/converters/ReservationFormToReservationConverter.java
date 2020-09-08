package app.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import app.dto.Reservation;
import app.formholder.ReservationForm;

@Component
public class ReservationFormToReservationConverter implements Converter<ReservationForm, Reservation>{

	@Override
	public Reservation convert(ReservationForm source) {
		Reservation reservation = new Reservation();
		reservation.setId(source.getId());
		reservation.setFrom(source.getTimeFrom().getTime());
		reservation.setTo(source.getTimeTo().getTime());
		reservation.setRoom(source.getRoom());
		reservation.setOwner(source.getOwner());
		return reservation;
	}

}
