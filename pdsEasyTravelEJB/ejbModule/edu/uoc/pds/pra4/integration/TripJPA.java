package edu.uoc.pds.pra4.integration;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import edu.uoc.pds.pra4.business.tripadministration.TripAdministrationException;
import edu.uoc.pds.pra4.exception.EasyTravelException;
import edu.uoc.pds.pra4.exception.ValidationError;

/**
 * JPA Class DriverJPA
 */
@Entity
@Table(name="practicauoc.trip")

public class TripJPA implements Serializable, IValidable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id",columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String description;
	private String departureCity;
	private String fromPlace;
	private Date departureDate;
	private Date departureTime;
	private String arrivalCity;
	private String toPlace;
	private Integer availableSeats;
	private Double price;
	
	@ManyToOne
	@JoinColumn(	name = "nif", 
					referencedColumnName = "nif")
	private DriverJPA driverJPA;	
	
	
	@OneToMany (fetch = FetchType.LAZY, mappedBy = "tripJPA", cascade ={CascadeType.ALL })
	private Set<TripPassengerJPA> tripPassengers;
	
	
	



	/**
	 * 
	 * Class constructor methods
	 *
	 */
	public TripJPA() {
		super();
	}	


	
	public void update ( final TripJPA tripJPA ) {
		
		update(
		tripJPA.getDescription(), tripJPA.getDepartureCity(), tripJPA.getFromPlace(), tripJPA.getDepartureDate(),
		tripJPA.getDepartureTime(), tripJPA.getArrivalCity(), tripJPA.getToPlace(), tripJPA.getAvailableSeats(), tripJPA.getPrice()
		);
	}
	
	public void update ( String description, String departureCity, String fromPlace, Date departureDate,
			Date departureTime, String arrivalCity, String toPlace, Integer availableSeats, Double price) {
		
		this.description = description;
		this.departureCity = departureCity;
		this.fromPlace = fromPlace;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.arrivalCity = arrivalCity;
		this.toPlace = toPlace;
		this.availableSeats = availableSeats;
		this.price = price;
		
	}



	@Override
	public void validateCaseInsert() throws EasyTravelException {
		
		final List<ValidationError> errors = new ArrayList<ValidationError>();	
		
		if (description == null || description.equals("")) {
			errors.add( new ValidationError("description") );
		}
		if (departureCity == null || departureCity.equals("")) {
			errors.add( new ValidationError("departureCity") );
		}
		if (fromPlace == null|| fromPlace.equals("")) {
			errors.add( new ValidationError("fromPlace") );
		}
		if (departureDate == null|| departureDate.equals("")) {
			errors.add( new ValidationError("departureDate") );
		}
		if (departureTime == null|| departureTime.equals("")) {
			errors.add( new ValidationError("departureTime") );
		}
		if (arrivalCity == null|| arrivalCity.equals("")) {
			errors.add( new ValidationError("arrivalCity") );
		}
		if (toPlace == null|| toPlace.equals("")) {
			errors.add( new ValidationError("toPlace") );
		}
		if (availableSeats == null|| availableSeats.equals("")) {
			errors.add( new ValidationError("availableSeats") );
		}
		if (price == null|| price.equals("")) {
			errors.add( new ValidationError("price") );
		}
		
		if ( errors.size() > 0 ) {
			throw new TripAdministrationException(errors);
		}	
	}
	
	@Override
	public void validateCaseUpdate() throws EasyTravelException {		
		
		final List<ValidationError> errors = new ArrayList<ValidationError>();	
		
		if (id == null ) {
			errors.add( new ValidationError("id") );
		}
		
		if (description == null || description.equals("")) {
			errors.add( new ValidationError("description") );
		}
		if (departureCity == null || departureCity.equals("")) {
			errors.add( new ValidationError("departureCity") );
		}
		if (fromPlace == null|| fromPlace.equals("")) {
			errors.add( new ValidationError("fromPlace") );
		}
		if (departureDate == null|| departureDate.equals("")) {
			errors.add( new ValidationError("departureDate") );
		}
		if (departureTime == null|| departureTime.equals("")) {
			errors.add( new ValidationError("departureTime") );
		}
		if (arrivalCity == null|| arrivalCity.equals("")) {
			errors.add( new ValidationError("arrivalCity") );
		}
		if (toPlace == null|| toPlace.equals("")) {
			errors.add( new ValidationError("toPlace") );
		}
		if (availableSeats == null|| availableSeats.equals("")) {
			errors.add( new ValidationError("availableSeats") );
		}
		if (price == null|| price.equals("")) {
			errors.add( new ValidationError("price") );
		}
		
		if ( errors.size() > 0 ) {
			throw new TripAdministrationException(errors);
		}		
	}


	/**
	 * Get passengers from trip
	 * @return
	 */
	public Set<PassengerJPA> getPassengers() {
		
		final Set<PassengerJPA> passengersJPA = new HashSet<PassengerJPA>();
		
		if ( tripPassengers != null ) {
			
			Iterator<TripPassengerJPA> iterator = tripPassengers.iterator();
			while ( iterator.hasNext() ) {
				TripPassengerJPA tripPassengerJPA = iterator.next();
				passengersJPA.add( tripPassengerJPA.getPassengerJPA() );
			}
		}
		
		return passengersJPA;
	}
	
	

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getDepartureCity() {
		return departureCity;
	}



	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}



	public String getFromPlace() {
		return fromPlace;
	}



	public void setFromPlace(String fromPlace) {
		this.fromPlace = fromPlace;
	}



	public Date getDepartureDate() {
		return departureDate;
	}



	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}



	public Date getDepartureTime() {
		return departureTime;
	}



	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}



	public String getArrivalCity() {
		return arrivalCity;
	}



	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}



	public String getToPlace() {
		return toPlace;
	}



	public void setToPlace(String toPlace) {
		this.toPlace = toPlace;
	}



	public Integer getAvailableSeats() {
		return availableSeats;
	}



	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}



	public Double getPrice() {
		return price;
	}



	public void setPrice(Double price) {
		this.price = price;
	}



	public DriverJPA getDriverJPA() {
		return driverJPA;
	}



	public void setDriverJPA(DriverJPA driverJPA) {
		this.driverJPA = driverJPA;
	}



	public Set<TripPassengerJPA> getTripPassengers() {
		return tripPassengers;
	}



	public void setTripPassengers(Set<TripPassengerJPA> tripPassengers) {
		this.tripPassengers = tripPassengers;
	}



	
}
