package app.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import app.dao.DaoFactory;
import app.dao.RoomDao;
import app.dto.Room;

@Component
public class RoomConverter implements Converter<String, Room>{

	private RoomDao roomDao;
	
	public RoomConverter(DaoFactory daoFactory) {
		roomDao = daoFactory.getRoomDao();
	}
	
	@Override
	public Room convert(String source) {
		if(source == null || source.isEmpty() || "null".equals(source)) {
			return null;
		}
		return roomDao.find(Long.parseLong(source));
	}

}
