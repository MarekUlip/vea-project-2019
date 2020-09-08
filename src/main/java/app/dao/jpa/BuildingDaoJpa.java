package app.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import app.dao.BuildingDao;
import app.dto.Building;
import app.dto.BuildingOwner;

@Repository
@Transactional
public class BuildingDaoJpa implements BuildingDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Building> getAll() {
		return em.createQuery("select b from Building b",Building.class).getResultList();
	}

	@Override
	public void edit(Building building) {
		BuildingOwner oldOwner = find(building.getId()).getOwner();
		if(oldOwner != null) {
			em.refresh(oldOwner);
		}
		
		Building mergedBuilding = em.merge(building);
		if(mergedBuilding.getOwner() != null) {
			em.refresh(mergedBuilding.getOwner());
		}
		
	}

	@Override
	public void delete(Building building) {
		if(!em.contains(building)) {
			building = em.merge(building);
		}
		em.remove(building);
		
	}

	@Override
	public void create(Building building) {
		em.persist(building);
	}

	@Override
	public Building find(long id) {
		return em.find(Building.class, id);
	}


}
