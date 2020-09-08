package app.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.dao.BuildingDao;
import app.dao.DaoFactory;
import app.dao.ReservationDao;
import app.dao.RoomDao;
import app.dao.UserDao;


public class JdbcDaoFactory implements DaoFactory{
    
    @Autowired
	private BuildingDaoJdbc buildingDao;
	@Autowired
	private ReservationDaoJdbc reservationDao;
	@Autowired
	private RoomDaoJdbc roomDao;
	@Autowired
	private UserDaoJdbc userDao;
	

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
