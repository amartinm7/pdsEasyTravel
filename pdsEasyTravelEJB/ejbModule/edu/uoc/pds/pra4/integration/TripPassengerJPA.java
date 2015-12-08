package edu.uoc.pds.pra4.integration;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TripPassengerJPA
 * @author amm
 */
@Entity
@Table(name="practicauoc.trip_passenger")
public class TripPassengerJPA implements Serializable {
	
	/**
	 * SerialID
	 */
	private static final long serialVersionUID = 2243260747077885892L;


	@EmbeddedId
	private TripPassengerID id;
	
	
	@ManyToOne
	@JoinColumn(	name = "id_trip", referencedColumnName = "id",
					 insertable = false, updatable = false)
	private TripJPA tripJPA;
	
	
	@ManyToOne
	@JoinColumn(	name = "nif_passenger", referencedColumnName = "nif",
				    insertable = false, updatable = false)
	private PassengerJPA passengerJPA;


	
	public TripPassengerJPA(){
		super();
	}
	
	
	
	
	public TripPassengerID getId() {
		return id;
	}


	public void setId(TripPassengerID id) {
		this.id = id;
	}


	public TripJPA getTripJPA() {
		return tripJPA;
	}


	public void setTripJPA(TripJPA tripJPA) {
		this.tripJPA = tripJPA;
	}


	public PassengerJPA getPassengerJPA() {
		return passengerJPA;
	}


	public void setPassengerJPA(PassengerJPA passengerJPA) {
		this.passengerJPA = passengerJPA;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		TripPassengerJPA other = (TripPassengerJPA) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}




	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TripPassengerJPA [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
}
