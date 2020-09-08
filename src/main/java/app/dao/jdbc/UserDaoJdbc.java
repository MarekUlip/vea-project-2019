package app.dao.jdbc;

import java.util.List;

import org.springframework.stereotype.Repository;

import app.dao.UserDao;
import app.dto.AbstractUser;
import app.dto.AppUser;
import app.dto.BuildingOwner;

@Repository
public class UserDaoJdbc implements UserDao {

	@Override
	public List<AbstractUser> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BuildingOwner> getAllBuildingOwners() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppUser> getAllAppUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void edit(AbstractUser user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(AbstractUser user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createBuildingOwner(BuildingOwner user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createAppUser(AppUser user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AbstractUser find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractUser findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
