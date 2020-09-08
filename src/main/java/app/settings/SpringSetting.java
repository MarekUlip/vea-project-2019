package app.settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import app.converters.AbstractUserToUserFormConverter;
import app.converters.BuildingConverter;
import app.converters.DateToStringConverter;
import app.converters.ReservationFormToReservationConverter;
import app.converters.ReservationToReservationFormConverter;
import app.converters.RoomConverter;
import app.converters.UserFormToAbstractUserConverter;

@Configuration
public class SpringSetting implements WebMvcConfigurer {

	@Autowired
	private AbstractUserToUserFormConverter abstractUserToUserFormConverter;
	@Autowired
	private UserFormToAbstractUserConverter userFormToAbstractUserConverter;
	@Autowired
	private ReservationFormToReservationConverter reservationFormToReservationConverter;
	@Autowired 
	private ReservationToReservationFormConverter reservationToReservationFormConverter;
	@Autowired
	private BuildingConverter buildingConverter;
	@Autowired
	private RoomConverter roomConverter;
	@Autowired
	private DateToStringConverter dateToStringConverter;
	//TODO private PersonConverter personConverter;
	
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(abstractUserToUserFormConverter);
		registry.addConverter(userFormToAbstractUserConverter);
		registry.addConverter(reservationFormToReservationConverter);
		registry.addConverter(reservationToReservationFormConverter);
		registry.addConverter(buildingConverter);
		registry.addConverter(roomConverter);
		registry.addConverter(dateToStringConverter);
	}
	

}