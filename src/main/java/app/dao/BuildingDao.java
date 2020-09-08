package app.dao;

import java.util.List;

import app.dto.Building;

public interface BuildingDao {
	List<Building> getAll();
	void edit(Building building);
	void delete(Building building);
	void create(Building building);
	Building find(long id);
}
