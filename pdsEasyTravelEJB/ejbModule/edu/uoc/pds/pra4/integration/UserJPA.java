package edu.uoc.pds.pra4.integration;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import edu.uoc.pds.pra4.business.useradministration.UserAdministrationException;
import edu.uoc.pds.pra4.exception.EasyTravelException;
import edu.uoc.pds.pra4.exception.ValidationError;

/**
 * UserJPA
 * @author amm
 */
@Entity
@Table( name="practicauoc.v_user" )
public class UserJPA implements IUserJPA{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7011866407340872540L;
	
	@Id
	private String nif;
	
	private String name;
	
	private String surname;
	
	private String phone;
	
	private String password;
	
	private String email;
	
	private String userType;



	/**
	 * default constructor
	 */
	public UserJPA(){
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
	public UserJPA( String nif, String name, String surname, String phone, String password, String email, String userType) throws EasyTravelException {
		super();
		this.nif = nif;
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.password = password;
		this.email = email;
		this.userType = userType;
		validate();
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserJPA [");
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
		if (userType != null) {
			builder.append("userType=");
			builder.append(userType);
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
			System.err.println( String.format (  "%s : %s ", this.getClass(), errors));
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
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public boolean isDriver() {
		return getUserType().equals( DriverJPA.USER_TYPE );
	}
	 
	
	
}

