package app.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import app.dao.RoomDao;
import app.dto.Building;
import app.dto.Room;

@Repository
@Transactional
public class RoomDaoJpa implements RoomDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Room> getAll() {
		return em.createQuery("select r from Room r",Room.class).getResultList();
	}

	@Override
	public void edit(Room room) {
		Building oldBuilding = find(room.getId()).getBuilding();
		if(oldBuilding != null) {
			em.refresh(oldBuilding);
		}
		
		Room mergedRoom= em.merge(room);
		if(mergedRoom.getBuilding() != null) {
			em.refresh(mergedRoom.getBuilding());
		}
	}

	@Override
	public void delete(Room room) {
		if(!em.contains(room)) {
			room = em.merge(room);
		}
		em.remove(room);
	}

	@Override
	public void create(Room room) {
		em.persist(room);
	}

	@Override
	public Room find(long id) {
		return em.find(Room.class, id);
	}

	@Override
	public List<Room> getAllRoomsFromBuilding(Building building) {
		return em.createQuery("select r from Room r where r.building = :value1",Room.class).setParameter("value1", building).getResultList();
	}

}
