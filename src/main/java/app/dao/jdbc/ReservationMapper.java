package app.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import app.dto.Reservation;

public class ReservationMapper implements RowMapper<Reservation>{

	@Override
	public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
		Reservation reservation = new Reservation();
		reservation.setFrom(rs.getLong("from"));
		reservation.setId(rs.getLong("id"));
		reservation.setTo(rs.getLong("to"));
		
		return reservation;
	}

}
