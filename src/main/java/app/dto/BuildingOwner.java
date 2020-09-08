package app.dto;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Entity;
@Entity
@DiscriminatorValue("B")
public class BuildingOwner extends AbstractUser{

	@OneToMany(mappedBy="owner")
	@JsonManagedReference
	private List<Building> ownedBuildings;

	public List<Building> getOwnedBuildings() {
		return ownedBuildings;
	}

	public void setOwnedBuildings(List<Building> ownedBuildings) {
		this.ownedBuildings = ownedBuildings;
	}


}
