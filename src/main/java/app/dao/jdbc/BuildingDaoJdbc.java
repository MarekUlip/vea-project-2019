package app.dao.jdbc;

import java.util.List;

import org.springframework.stereotype.Repository;

import app.dao.BuildingDao;
import app.dto.Building;

@Repository
public class BuildingDaoJdbc implements BuildingDao{

	@Override
	public List<Building> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Building building) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Building find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void edit(Building building) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create(Building building) {
		// TODO Auto-generated method stub
		
	}

}
