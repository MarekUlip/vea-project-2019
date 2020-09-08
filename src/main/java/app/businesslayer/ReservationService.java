package app.businesslayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dao.DaoFactory;
import app.dao.ReservationDao;
import app.dto.Reservation;
import app.dto.Room;

@Service
public class ReservationService {
	
	private ReservationDao reservationDao;
	
	@Autowired
	public void setDaoFactory(DaoFactory daoFactory) {
		reservationDao = daoFactory.getReservationDao();
	}
	
	public List<Reservation> getAllReservations(){
		return reservationDao.getAll();
	}
	
	public void create(Reservation reservation) {
		reservationDao.create(reservation);
	}
	
	public void edit(Reservation reservation) {
		reservationDao.edit(reservation);
	}
	
	public void delete(Reservation reservation) {
		reservationDao.delete(reservation);
	}
	
	public Reservation find(long id) {
		return reservationDao.find(id);
	}
	
	public List<Reservation> getReservationsBetween(long from, long to){
		return reservationDao.getReservationsBetween(from, to);
	}
	public List<Reservation> getReservationsBetweenFromRoom(long from, long to,Room room){
		return reservationDao.getReservationsBetweenFromRoom(from, to, room);
	}
	public List<Reservation> getAllReservationsFromRoom(Room r){
		return getAllReservationsFromRoom(r);
	}
}
