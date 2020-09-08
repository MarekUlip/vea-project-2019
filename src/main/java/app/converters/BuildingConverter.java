package app.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import app.dao.BuildingDao;
import app.dao.DaoFactory;
import app.dto.Building;

@Component
public class BuildingConverter implements Converter<String, Building>{

	private BuildingDao buildingDao;
	
	public BuildingConverter(DaoFactory daoFactory) {
		buildingDao = daoFactory.getBuildingDao();
	}
	
	@Override
	public Building convert(String source) {
		if(source == null || source.isEmpty() || "null".equals(source)) {
			return null;
		}
		return buildingDao.find(Long.parseLong(source));
	}

}
