package app.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import app.dto.AbstractUser;
import app.dto.BuildingOwner;
import app.formholder.UserForm;

@Component
public class AbstractUserToUserFormConverter implements Converter<AbstractUser, UserForm>{

	@Override
	public UserForm convert(AbstractUser user) {
		UserForm form = new UserForm();
		form.setId(user.getId());
		form.setEmail(user.getEmail());
		form.setName(user.getName());
		form.setPassword(user.getPassword());
		form.setSurname(user.getSurname());
		form.setIsBuildingOwner(user instanceof BuildingOwner);
		return form;
	}

}
