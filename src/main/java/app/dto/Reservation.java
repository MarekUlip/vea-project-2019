package app.dto;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;

@Entity
public class Reservation{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JsonBackReference
	private AbstractUser owner;
	private long timeFrom;
	private long timeTo;
	@ManyToOne
	@JsonBackReference
	private Room room;
	
	
	public Room getRoom() {
		return room;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		if (id != other.id)
			return false;
		return true;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
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
	public long getFrom() {
		return timeFrom;
	}
	public void setFrom(long from) {
		this.timeFrom = from;
	}
	public long getTo() {
		return timeTo;
	}
	public void setTo(long to) {
		this.timeTo = to;
	}

}
