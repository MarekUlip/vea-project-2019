package app.dao;

public interface DaoFactory {

    public BuildingDao getBuildingDao();
    public ReservationDao getReservationDao();
    public RoomDao getRoomDao();
    public UserDao getUserDao();
    
}
