package app.dao;

import java.util.List;

import app.dto.Building;
import app.dto.Room;

public interface RoomDao {

	List<Room> getAll();
	void edit(Room room);
	void delete(Room room);
	void create(Room room);
	List<Room> getAllRoomsFromBuilding(Building building);
	Room find(long id);
}
