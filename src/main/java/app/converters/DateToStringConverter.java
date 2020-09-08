package app.converters;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class DateToStringConverter implements Converter<Date, String>{

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
	@Override
	public String convert(Date source) {
		return dateFormat.format(source);
	}

}
