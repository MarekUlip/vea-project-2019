package app.businesslayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import app.dao.DaoFactory;
import app.dao.ReservationDao;
import app.dao.UserDao;
import app.dto.AbstractUser;
import app.dto.AppUser;
import app.dto.BuildingOwner;
import app.dto.Reservation;

@Service
public class UserService {
	private ReservationDao reservationDao;
	private UserDao userDao;
	
	@Autowired
	public void setDaoFactory(DaoFactory daoFactory) {
		reservationDao = daoFactory.getReservationDao();
		userDao = daoFactory.getUserDao();
	}
	public List<AbstractUser> getAll(){
		return userDao.getAll();
	}
	public List<BuildingOwner> getAllBuildingOwners(){
		return userDao.getAllBuildingOwners();
	}
	public List<AppUser> getAllAppUsers(){
		return userDao.getAllAppUsers();
	}
	
	public void edit(AbstractUser user) {
		userDao.edit(user);
	}
	public void delete(AbstractUser user) {
		for (Reservation reservation : reservationDao.getReservationsFromUser(user)) {
			reservationDao.delete(reservation);
		}
		userDao.delete(user);
	}
	//void create(User user);
	public void createBuildingOwner(BuildingOwner user){
		userDao.createBuildingOwner(user);
	}
	public void createAppUser(AppUser user) {
		userDao.createAppUser(user);
	}
	public AbstractUser find(long id) {
		return userDao.find(id);
	}
	public AbstractUser findByEmail(String email) {
		return userDao.findByEmail(email);
	}
	
	public String getLoggedUserEmail() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    return currentUserName;
		}
		return null;
		
	}
	
	public AbstractUser getLoggedUser() {
		return userDao.findByEmail(getLoggedUserEmail());
	}
	
	public boolean isBuildingOwner() {
		AbstractUser user = userDao.findByEmail(getLoggedUserEmail());
		System.out.println("testing");
		System.out.println(user);
		System.out.println(user instanceof BuildingOwner);
		return user instanceof BuildingOwner;//user.getUType().equals("B");
	}
}
