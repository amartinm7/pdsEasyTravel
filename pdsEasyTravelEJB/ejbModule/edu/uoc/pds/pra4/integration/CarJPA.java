package edu.uoc.pds.pra4.integration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import edu.uoc.pds.pra4.business.useradministration.UserAdministrationException;
import edu.uoc.pds.pra4.exception.EasyTravelException;
import edu.uoc.pds.pra4.exception.ValidationError;

/**
 * CarJPA
 * @author amm
 */
@Entity
@Table(name="practicauoc.car")
public class CarJPA implements Serializable, IValidable{

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 5407113969508679530L;
	
	
	@Id
	@Column ( name = "id", columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer carRegistrationId;
	
	private String brand;
	
	private String model;
	
	private String color;
	
	@ManyToOne
	@JoinColumn(	name = "nif_owner", 
					referencedColumnName = "nif")
	private DriverJPA driverJPA;
	
	


	/**
	 * default constructor
	 */
	public CarJPA() {
		super();
	}
	
	/**
	 * constructor caso insert
	 * @param carRegistrationId
	 * @param brand
	 * @param model
	 * @param color
	 * @param driverJPA
	 */
	public CarJPA( 
			final String brand, 
			final String model, 
			final String color, 
			final DriverJPA driverJPA ) {
		
		super();
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.driverJPA = driverJPA;
	}
	
	/**
	 * constructor caso update
	 * @param carRegistrationId
	 * @param brand
	 * @param model
	 * @param color
	 * @param driverJPA
	 */
	public CarJPA( 
			final Integer carRegistrationId, 
			final String brand, 
			final String model, 
			final String color, 
			final DriverJPA driverJPA ) {
		
		super();
		this.carRegistrationId = carRegistrationId;
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.driverJPA = driverJPA;
	}


	public Integer getCarRegistrationId() {
		return carRegistrationId;
	}


	public void setCarRegistrationId(Integer carRegistrationId) {
		this.carRegistrationId = carRegistrationId;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}
	
	public DriverJPA getDriverJPA() {
		return driverJPA;
	}


	public void setDriverJPA(DriverJPA driverJPA) {
		this.driverJPA = driverJPA;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CarJPA [");
		if (carRegistrationId != null) {
			builder.append("carRegistrationId=");
			builder.append(carRegistrationId);
			builder.append(", ");
		}
		if (brand != null) {
			builder.append("brand=");
			builder.append(brand);
			builder.append(", ");
		}
		if (model != null) {
			builder.append("model=");
			builder.append(model);
			builder.append(", ");
		}
		if (color != null) {
			builder.append("color=");
			builder.append(color);
			builder.append(", ");
		}
		if (driverJPA != null) {
			builder.append("driverJPA=");
			builder.append(driverJPA);
		}
		builder.append("]");
		return builder.toString();
	}
	
	
	@Override
	public void validateCaseInsert() throws EasyTravelException {
		
		final List<ValidationError> errors = new ArrayList<ValidationError>();
		
		if (brand == null || brand.equals("")) {
			errors.add( new ValidationError("brand") );
		}
		if (model == null|| model.equals("")) {
			errors.add( new ValidationError("model") );
		}
		if (color == null|| color.equals("")) {
			errors.add( new ValidationError("color") );
		}
		if (driverJPA == null) {
			errors.add( new ValidationError("driver") );
		}
		
		if ( errors.size() > 0 ) {
			System.err.println( String.format ( "%s : %s ", this.getClass(), errors));
			throw new UserAdministrationException(errors);
		}
	}
	
	@Override
	public void validateCaseUpdate() throws EasyTravelException {
		
		final List<ValidationError> errors = new ArrayList<ValidationError>();
		
		if (carRegistrationId == null || carRegistrationId == 0) {
			errors.add( new ValidationError("carRegistrationId") );
		}
		if (brand == null || brand.equals("")) {
			errors.add( new ValidationError("brand") );
		}
		if (model == null|| model.equals("")) {
			errors.add( new ValidationError("model") );
		}
		if (color == null|| color.equals("")) {
			errors.add( new ValidationError("color") );
		}
		if (driverJPA == null) {
			errors.add( new ValidationError("driver") );
		}
		
		if ( errors.size() > 0 ) {
			System.err.println( String.format ( "%s : %s ", this.getClass(), errors));
			throw new UserAdministrationException(errors);
		}
	}
	
	
	public void validateCaseDelete() throws EasyTravelException {
		final List<ValidationError> errors = new ArrayList<ValidationError>();
		
		if (carRegistrationId == null || carRegistrationId == 0) {
			errors.add( new ValidationError("carRegistrationId") );
		}
		
		if ( errors.size() > 0 ) {
			System.err.println( String.format ( "%s : %s ", this.getClass(), errors));
			throw new UserAdministrationException(errors);
		}
	}
	
	
}
