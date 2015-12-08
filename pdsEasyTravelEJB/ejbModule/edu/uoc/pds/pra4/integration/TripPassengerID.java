package edu.uoc.pds.pra4.integration;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TripPassengerID
 * @author amm
 */
@Embeddable
public class TripPassengerID implements Serializable {

	/**
	 * SerialID
	 */
	private static final long serialVersionUID = -4612860006919029860L;
	
	@Column(name="id_trip", insertable=true, updatable=true)
	private Integer idTrip;
	
	@Column(name="nif_passenger", insertable=true, updatable=true)
	private String nifPassenger;

	
	public TripPassengerID(){
		super();
	}
			
	
	public TripPassengerID(Integer idTrip, String nifPassenger) {
		super();
		this.idTrip = idTrip;
		this.nifPassenger = nifPassenger;
	}


	public Integer getIdTrip() {
		return idTrip;
	}


	public void setIdTrip(Integer idTrip) {
		this.idTrip = idTrip;
	}


	public String getNifPassenger() {
		return nifPassenger;
	}


	public void setNifPassenger(String nifPassenger) {
		this.nifPassenger = nifPassenger;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTrip == null) ? 0 : idTrip.hashCode());
		result = prime * result + ((nifPassenger == null) ? 0 : nifPassenger.hashCode());
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
		TripPassengerID other = (TripPassengerID) obj;
		if (idTrip == null) {
			if (other.idTrip != null)
				return false;
		} else if (!idTrip.equals(other.idTrip))
			return false;
		if (nifPassenger == null) {
			if (other.nifPassenger != null)
				return false;
		} else if (!nifPassenger.equals(other.nifPassenger))
			return false;
		return true;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TripPassengerID [");
		if (idTrip != null) {
			builder.append("idTrip=");
			builder.append(idTrip);
			builder.append(", ");
		}
		if (nifPassenger != null) {
			builder.append("nifPassenger=");
			builder.append(nifPassenger);
		}
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
