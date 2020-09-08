package app.formholder;

import java.util.Date;


import org.springframework.format.annotation.DateTimeFormat;

import app.dto.AbstractUser;
import app.dto.Room;

public class ReservationForm {
	private long id;
	private AbstractUser owner;
	@DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
	private Date timeFrom;
	@DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
	private Date timeTo;
	private Room room;
	private boolean isEditable;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public AbstractUser getOwner() {
		return owner;
	}
	public void setOwner(AbstractUser owner) {
		this.owner = owner;
	}
	public Date getTimeFrom() {
		return timeFrom;
	}
	public void setTimeFrom(Date timeFrom) {
		this.timeFrom = timeFrom;
	}
	public Date getTimeTo() {
		return timeTo;
	}
	public void setTimeTo(Date timeTo) {
		this.timeTo = timeTo;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public boolean getIsEditable() {
		return isEditable;
	}
	public void setIsEditable(boolean isEditable) {
		this.isEditable = isEditable;
	}
	
}
