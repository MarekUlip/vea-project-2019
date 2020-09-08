package app.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import app.dao.UserDao;
import app.dto.AbstractUser;
import app.dto.AppUser;
import app.dto.BuildingOwner;

@Repository
@Transactional
public class UserDaoJpa implements UserDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<AbstractUser> getAll() {
		return em.createQuery("select u from AbstractUser u",AbstractUser.class).getResultList();
	}
	
	@Override
	public List<BuildingOwner> getAllBuildingOwners() {
		return em.createQuery("select u from AbstractUser u where u.USER_TYPE = 'B'",BuildingOwner.class).getResultList();
	}

	@Override
	public List<AppUser> getAllAppUsers() {
		return em.createQuery("select u from AbstractUser u where u.USER_TYPE = 'N'",AppUser.class).getResultList();
	}
	
	@Override
	public void createBuildingOwner(BuildingOwner user) {
		em.persist(user);
	}

	@Override
	public void createAppUser(AppUser user) {
		em.persist(user);
	}

	@Override
	public void edit(AbstractUser user) {
		/*if(user.getUType().equals("N")) {
			em.merge((AppUser)user);
		}else {
			em.merge((BuildingOwner)user);
		}*/
		em.merge(user);
	}

	@Override
	public void delete(AbstractUser user) {
		// TODO Auto-generated method stub
		if(!user.getReservation().isEmpty())return;
		if(user instanceof AppUser) {//user.getUType().equals("N")) {
			em.remove(em.merge((AppUser)user));
		}else {
			if(((BuildingOwner)user).getOwnedBuildings().isEmpty())em.remove(em.merge((BuildingOwner)user));
		}
	}


	@Override
	public AbstractUser find(long id) {
		return em.find(AbstractUser.class, id);
	}

	@Override
	public AbstractUser findByEmail(String email) {
		try{
			return em.createQuery("SELECT u FROM AbstractUser u where u.email = :value1",AbstractUser.class).setParameter("value1", email).getSingleResult();
		} catch(Exception e) {
			System.out.println("Something went bad");
			e.printStackTrace();
			return null;
		}
                
	}

}
