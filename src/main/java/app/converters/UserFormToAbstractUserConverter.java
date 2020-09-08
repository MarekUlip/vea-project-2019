package app.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import app.dto.AbstractUser;
import app.dto.AppUser;
import app.dto.BuildingOwner;
import app.formholder.UserForm;

@Component
public class UserFormToAbstractUserConverter implements Converter<UserForm, AbstractUser>{

	@Override
	public AbstractUser convert(UserForm form) {
		AbstractUser user;
		if(form.getIsBuildingOwner()) {
			user = new BuildingOwner();
		} else {
			user = new AppUser();
		}
		user.setId(form.getId());
		user.setEmail(form.getEmail());
		user.setName(form.getName());
		user.setPassword(form.getPassword());
		user.setSurname(form.getSurname());
		return user;
	}
	
}
