package app.dao.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import app.dao.BuildingDao;
import app.dao.DaoFactory;
import app.dao.ReservationDao;
import app.dao.RoomDao;
import app.dao.UserDao;

@Primary
@Component
public class JpaDaoFactory implements DaoFactory{

	@Autowired
	private BuildingDaoJpa buildingDao;
	@Autowired
	private ReservationDaoJpa reservationDao;
	@Autowired
	private RoomDaoJpa roomDao;
	@Autowired
	private UserDaoJpa userDao;
	

	@Override
	public BuildingDao getBuildingDao() {
		return buildingDao;
	}

	@Override
	public ReservationDao getReservationDao() {
		return reservationDao;
	}

	@Override
	public RoomDao getRoomDao() {
		return roomDao;
	}

	@Override
	public UserDao getUserDao() {
		return userDao;
	}

    
}
