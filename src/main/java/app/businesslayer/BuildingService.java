package app.businesslayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.BuildingDao;
import app.dao.DaoFactory;
import app.dao.ReservationDao;
import app.dao.RoomDao;
import app.dto.Building;
import app.dto.Reservation;
import app.dto.Room;

@Service
public class BuildingService {
	private BuildingDao buildingDao;
	private RoomDao roomDao;
	private ReservationDao reservationDao;
	
	@Autowired
	public void setDaoFactory(DaoFactory daoFactory) {
		buildingDao = daoFactory.getBuildingDao();
		roomDao = daoFactory.getRoomDao();
		reservationDao = daoFactory.getReservationDao();
	}
	
	public List<Building> getAllBuildings(){
		return buildingDao.getAll();
	}
	
	public void create(Building building) {
		buildingDao.create(building);
	}
	
	public void edit(Building building) {
		buildingDao.edit(building);
	}
	
	public void delete(Building building) {
		for (Room room : roomDao.getAllRoomsFromBuilding(building)) {
			for(Reservation reservation: reservationDao.getAllReservationsFromRoom(room)) {
				reservationDao.delete(reservation);
			}
			roomDao.delete(room);
		}
		buildingDao.delete(building);
	}
	
	public Building find(long id) {
		return buildingDao.find(id);
	}
}
