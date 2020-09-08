package app.dao;

import java.util.List;

import app.dto.AbstractUser;
import app.dto.Reservation;
import app.dto.Room;

public interface ReservationDao {
	List<Reservation> getAll();
	List<Reservation> getAllReservationsFromRoom(Room r);
	void edit(Reservation reservation);
	void delete(Reservation reservation);
	void create(Reservation reservation);
	Reservation find(long id);
	List<Reservation> getReservationsBetween(long from, long to);
	List<Reservation> getReservationsBetweenFromRoom(long from, long to,Room room);
	List<Reservation> getReservationsFromUser(AbstractUser user);
}
