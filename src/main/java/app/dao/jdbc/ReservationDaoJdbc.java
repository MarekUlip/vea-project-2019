package app.dao.jdbc;

import java.util.List;

import org.springframework.stereotype.Repository;

import app.dao.ReservationDao;
import app.dto.AbstractUser;
import app.dto.Reservation;
import app.dto.Room;

@Repository
public class ReservationDaoJdbc implements ReservationDao{

	@Override
	public List<Reservation> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> getAllReservationsFromRoom(Room r) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void edit(Reservation reservation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Reservation reservation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create(Reservation reservation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Reservation find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> getReservationsBetween(long from, long to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> getReservationsBetweenFromRoom(long from, long to, Room room) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> getReservationsFromUser(AbstractUser user) {
		// TODO Auto-generated method stub
		return null;
	}

}
