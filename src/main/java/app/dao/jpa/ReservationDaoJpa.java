package app.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import app.dao.ReservationDao;
import app.dto.AbstractUser;
import app.dto.Reservation;
import app.dto.Room;
@Repository
@Transactional
public class ReservationDaoJpa implements ReservationDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Reservation> getAll() {
		return em.createQuery("select r from Reservation r",Reservation.class).getResultList();
	}
	@Override
	public List<Reservation> getReservationsBetween(long from, long to) {
		return em.createQuery("SELECT r FROM Reservation r where (((r.timeFrom BETWEEN :value1 AND :value2) or (r.timeTo BETWEEN :value1 AND :value2)) or ((:value1 BETWEEN r.timeFrom AND r.timeTo) or (:value2 BETWEEN r.timeFrom AND r.timeTo)))",Reservation.class)
        .setParameter("value1", from).setParameter("value2", to).getResultList();
	}
	

	@Override
	public List<Reservation> getReservationsBetweenFromRoom(long from, long to, Room room) {
		return em.createQuery("SELECT r FROM Reservation r where r.room = :value3 AND (((r.timeFrom BETWEEN :value1 AND :value2) or (r.timeTo BETWEEN :value1 AND :value2)) or ((:value1 BETWEEN r.timeFrom AND r.timeTo) or (:value2 BETWEEN r.timeFrom AND r.timeTo)))",Reservation.class)
		        .setParameter("value1", from).setParameter("value2", to).setParameter("value3", room).getResultList();
	}

	@Override
	public void edit(Reservation reservation) {
		
		Room oldRoom = find(reservation.getId()).getRoom();
		if(oldRoom != null) {
			em.refresh(oldRoom);
		}
		
		AbstractUser oldUser = find(reservation.getId()).getOwner();
		if(oldUser != null) {
			em.refresh(oldUser);
		}
		
		Reservation mergedReservation = em.merge(reservation);
		if(mergedReservation.getOwner() != null) {
			em.refresh(mergedReservation.getOwner());
		}
		if(mergedReservation.getRoom() != null) {
			em.refresh(mergedReservation.getRoom());
		}
	}

	@Override
	public void delete(Reservation reservation) {
		if(!em.contains(reservation)) {
			reservation = em.merge(reservation);
		}
		em.remove(reservation);
	}

	@Override
	public void create(Reservation reservation) {
		em.persist(reservation);
		
	}

	@Override
	public Reservation find(long id) {
		return em.find(Reservation.class, id);
	}

	@Override
	public List<Reservation> getAllReservationsFromRoom(Room r) {
		return em.createQuery("select r from Reservation r where r.room = :value1",Reservation.class).setParameter("value1", r).getResultList();
	}
	@Override
	public List<Reservation> getReservationsFromUser(AbstractUser user) {
		return em.createQuery("select r from Reservation r where r.user = :value1",Reservation.class).setParameter("value1", user).getResultList();
	}

}
