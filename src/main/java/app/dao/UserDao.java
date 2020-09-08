package app.dao;

import java.util.List;

import app.dto.AbstractUser;
import app.dto.AppUser;
import app.dto.BuildingOwner;

public interface UserDao {

	List<AbstractUser> getAll();
	List<BuildingOwner> getAllBuildingOwners();
	List<AppUser> getAllAppUsers();
	void edit(AbstractUser user);
	void delete(AbstractUser user);
	//void create(User user);
	void createBuildingOwner(BuildingOwner user);
	void createAppUser(AppUser user);
	AbstractUser find(long id);
	AbstractUser findByEmail(String email);
}
