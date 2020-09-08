package app.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import app.dto.AbstractUser;

public class AbstractUserMapper implements RowMapper<AbstractUser> {

	@Override
	public AbstractUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		AbstractUser user = new AbstractUser();
		user.setId(rs.getLong("id"));
		user.setEmail(rs.getString("email"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		user.setSurname(rs.getString("surname"));
		//user.setUType(rs.getString("utype"));
		return user;
	}

}
