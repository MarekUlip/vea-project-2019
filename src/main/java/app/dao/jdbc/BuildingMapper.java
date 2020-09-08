package app.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import app.dto.Building;
import app.dto.BuildingOwner;

public class BuildingMapper implements RowMapper<Building>{

	@Override
	public Building mapRow(ResultSet rs, int rowNum) throws SQLException {
		Building building = new Building();
		building.setCode(rs.getString("code"));
		building.setId(rs.getLong("id"));
		building.setStreet(rs.getString("street"));
		building.setTown(rs.getString("town"));
		/*BuildingOwner owner = new BuildingOwner();
		owner.setId(rs.getLong("owner"));
		building.setOwner(owner);*/
		return building;
	}

}
