package app.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import app.dto.Room;

public class RoomMapper implements RowMapper<Room>{

	@Override
	public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
		Room room = new Room();
		room.setId(rs.getLong("id"));
		room.setRoomName(rs.getString("roomName"));
		return room;
	}

}
