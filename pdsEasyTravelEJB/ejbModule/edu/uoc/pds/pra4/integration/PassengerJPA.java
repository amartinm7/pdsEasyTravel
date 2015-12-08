package edu.uoc.pds.pra4.integration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import edu.uoc.pds.pra4.business.useradministration.UserAdministrationException;
import edu.uoc.pds.pra4.exception.EasyTravelException;
import edu.uoc.pds.pra4.exception.ValidationError;

/**
 * 
 * JPA Class PassengerJPA
 *
 */
@Entity
@Table(name="practicauoc.passenger")

public class PassengerJPA implements IUserJPA {
	
	/**
	 * discriminator
	 */
	public static final String USER_TYPE = "passenger";
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String nif;
	
	private String name;
	private String surname;
	private String phone;
	private String password;
	private String email;	
	
	@javax.persistence.Transient
	private String userType = USER_TYPE;
	
	
	@OneToMany (fetch = FetchType.LAZY, mappedBy = "passengerJPA")
	private Set<TripPassengerJPA> tripPassengers;
	
	
	
	/**
	 * 
	 * Class constructor methods
	 *
	 */
	public PassengerJPA() {
		super();
	}	
	
	/**
	 * constructor insert/update
	 * @param nif
	 * @param name
	 * @param surname
	 * @param phone
	 * @param password
	 * @param email
	 */
	public PassengerJPA( String nif, String name, String surname, String phone, 
			String password, String email ) throws EasyTravelException {
		
		this.nif = nif;
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.password = password;
		this.email = email;
		
		validate();
	}
	
	public PassengerJPA ( IUserJPA userJPA ) throws EasyTravelException {
		
		this (userJPA.getNif(), userJPA.getName(), userJPA.getSurname(), userJPA.getPhone(), userJPA.getPassword(), userJPA.getEmail() );
	}
	


	private void validate() throws EasyTravelException {
		
		final List<ValidationError> errors = new ArrayList<ValidationError>();
		
		if (nif == null || nif.equals("")) {
			errors.add( new ValidationError("nif") );
		}
		if (name == null || name.equals("")) {
			errors.add( new ValidationError("name") );
		}
		if (surname == null|| surname.equals("")) {
			errors.add( new ValidationError("surname") );
		}
		if (phone == null|| phone.equals("")) {
			errors.add( new ValidationError("phone") );
		}
		if (password == null|| password.equals("")) {
			errors.add( new ValidationError("password") );
		}
		if (email == null|| email.equals("")) {
			errors.add( new ValidationError("email") );
		}
		
		if ( errors.size() > 0 ) {
			throw new UserAdministrationException(errors);
		}
	}
	
	@Override
	public void validateCaseInsert() throws EasyTravelException {
		validate();	
	}

	@Override
	public void validateCaseUpdate() throws EasyTravelException {
		validate();
	}
	
	
	@Override
	public boolean isDriver(){
		return false;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Set<TripPassengerJPA> getTripPassengers() {
		return tripPassengers;
	}

	public void setTripPassengers(Set<TripPassengerJPA> tripPassengers) {
		this.tripPassengers = tripPassengers;
	}
	
	public Set<TripJPA> getTrips() {
		
		final Set<TripJPA> tripsJPA = new HashSet<TripJPA>();
		
		if ( tripPassengers != null ) {
			
			Iterator<TripPassengerJPA> iterator = tripPassengers.iterator();
			while ( iterator.hasNext() ) {
				TripPassengerJPA tripPassengerJPA = iterator.next();
				tripsJPA.add( tripPassengerJPA.getTripJPA() );
			}
		}
		
		return tripsJPA;
	}
	
}
