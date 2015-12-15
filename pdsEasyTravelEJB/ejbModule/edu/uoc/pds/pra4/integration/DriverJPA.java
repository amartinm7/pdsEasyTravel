package edu.uoc.pds.pra4.integration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
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
 * JPA Class DriverJPA
 *
 */
@Entity
@Table(name="practicauoc.driver")

public class DriverJPA implements IUserJPA {
	
	/**
	 * discriminator
	 */
	public static final String USER_TYPE = "driver";
	
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
	
	@OneToMany (fetch = FetchType.LAZY, mappedBy = "driverJPA" , cascade = CascadeType.ALL)
	private Set<CarJPA> cars;
	
	@OneToMany (fetch = FetchType.LAZY, mappedBy = "driverJPA", cascade = CascadeType.ALL)
	private Set<TripJPA> trips;




	/**
	 * 
	 * Class constructor methods
	 *
	 */
	public DriverJPA() {
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
	public DriverJPA( String nif, String name, String surname, String phone, 
			String password, String email ) throws EasyTravelException {
		
		this.nif = nif;
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.password = password;
		this.email = email;
		validate();
	}
	
	public DriverJPA ( IUserJPA userJPA ) throws EasyTravelException {
		
		this (userJPA.getNif(), userJPA.getName(), userJPA.getSurname(), userJPA.getPhone(), userJPA.getPassword(), userJPA.getEmail() );
	}
	
	/**
	 * 
	 * Methods get/set the fields of database
	 * Id Primary Key field
	 */
	@Override
	public String getNif() {
		return nif;
	}
	@Override
	public void setNif(String nif) {
		this.nif = nif;		
	}
	
	@Override
	public String getName(){
		return name;
	}
	@Override
	public void setName(String name){
		this.name = name;
	}
	
	@Override
	public String getSurname(){
		return surname;
	}
	@Override
	public void setSurname(String surname){
		this.surname = surname;
	}
	
	@Override
	public String getPhone(){
		return phone;
	}
	@Override
	public void setPhone(String phone){
		this.phone = phone;
	}
	
	@Override
	public String getPassword(){
		return password;
	}
	@Override
	public void setPassword(String password){
		this.password = password;
	}
	
	@Override
	public String getEmail(){
		return email;
	}
	@Override
	public void setEmail(String email){
		this.email = email;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DriverJPA [");
		if (nif != null) {
			builder.append("nif=");
			builder.append(nif);
			builder.append(", ");
		}
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (surname != null) {
			builder.append("surname=");
			builder.append(surname);
			builder.append(", ");
		}
		if (phone != null) {
			builder.append("phone=");
			builder.append(phone);
			builder.append(", ");
		}
		if (password != null) {
			builder.append("password=");
			builder.append(password);
			builder.append(", ");
		}
		if (email != null) {
			builder.append("email=");
			builder.append(email);
		}
		builder.append("]");
		return builder.toString();
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
			System.err.println( String.format ( "%s : %s ", this.getClass(), errors));
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
	public String getUserType() {
		return userType;
	}

	@Override
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	@Override
	public boolean isDriver(){
		return false;
	}
	
	public Set<CarJPA> getCars() {
		if (cars == null) {
			cars = new HashSet<CarJPA>();
		}
		return cars;
	}

	public void setCars(Set<CarJPA> cars) {
		this.cars = cars;
	}

	public Set<TripJPA> getTrips() {
		if (trips == null) {
			trips = new HashSet<TripJPA>();
		}
		for (TripJPA tripJPA : trips) {
			tripJPA.getId();//inicializa los viajes, evita la excepcion de lazyInitializationException. No evitamos poner la relacion como eager
		}
		return trips;
	}

	public void setTrips(Set<TripJPA> trips) {
		this.trips = trips;
	}
	
	public void add (final TripJPA tripJPA){
		this.getTrips().add(tripJPA);
		tripJPA.setDriverJPA(this);
	}
}


