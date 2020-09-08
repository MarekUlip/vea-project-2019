package app.dao.jdbc;

import java.util.List;

import org.springframework.stereotype.Repository;

import app.dao.RoomDao;
import app.dto.Building;
import app.dto.Room;

@Repository
public class RoomDaoJdbc implements RoomDao {

	@Override
	public List<Room> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void edit(Room room) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Room room) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create(Room room) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Room find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> getAllRoomsFromBuilding(Building building) {
		// TODO Auto-generated method stub
		return null;
	}

}
