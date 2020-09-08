package app.businesslayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.DaoFactory;
import app.dao.ReservationDao;
import app.dao.RoomDao;
import app.dto.Reservation;
import app.dto.Room;

@Service
public class RoomService {
	private RoomDao roomDao;
	private ReservationDao reservationDao;
	
	@Autowired
	public void setDaoFactory(DaoFactory daoFactory) {
		roomDao = daoFactory.getRoomDao();
		reservationDao = daoFactory.getReservationDao();
	}
	
	public List<Room> getAllRooms(){
		return roomDao.getAll();
	}
	
	public void create(Room room) {
		roomDao.create(room);
	}
	
	public void edit(Room room) {
		roomDao.edit(room);
	}
	
	public void delete(Room room) {
		for(Reservation reservation: reservationDao.getAllReservationsFromRoom(room)) {
			reservationDao.delete(reservation);
		}
		roomDao.delete(room);
	}
	
	public Room find(long id) {
		return roomDao.find(id);
	}
}
