package edu.uoc.pds.pra4.business.useradministration;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import edu.uoc.pds.pra4.exception.EasyTravelException;
import edu.uoc.pds.pra4.exception.ValidationError;
import edu.uoc.pds.pra4.integration.IUserJPA;

/**
 * Ticket
 * @author amm
 */
public class Ticket implements ITicket{

	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 8356380793488762500L;
	
	private String nif;
	
	private String email;
	
	private String password;
	
	private boolean isValid;
	
	private Date expirationDate;
	
	private boolean driver;

	
	Ticket( final IUserJPA userJPA ) {
		
		super();
		this.email = userJPA.getEmail();
		this.password = userJPA.getPassword();
		this.isValid = true;
		this.expirationDate = newExpirationDate();
		this.nif = userJPA.getNif();
		this.driver = userJPA.isDriver();
	}

	
	Ticket(String email, String password, boolean isValid, Date expirationDate) {
		super();
		this.email = email;
		this.password = password;
		this.isValid = isValid;
		this.expirationDate = expirationDate;
		this.nif = "";
		this.driver = false;
	}




	private Date newExpirationDate(){
		
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.MINUTE, 30);
		
		return calendar.getTime();
		
	}
	
	/**
	 * crea un ticket expirado para no trabajar con referencias nulas de tickets
	 * @param email
	 * @param password
	 * @return
	 */
	public static Ticket getInvalidTicket (final String email, final String password) {
		
		return new Ticket(email, password, false, new Date() );
		
	}

	@Override
	public String getEmail() {
		return email;
	}

	void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean isValid() {
		return isValid;
	}

	void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	@Override
	public Date getExpirationDate() {
		return expirationDate;
	}

	void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	@Override
	public String getId() {
		return Integer.toString( hashCode() );
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		Ticket other = (Ticket) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}



	/**
	 * Indica si el cliente está autenticado en el sistema
	 * @throws EasyTravelException
	 */
	@Override
	public void validateTicket() throws EasyTravelException {
		
		if ( !this.isValid() ) {
			throw new UserAdministrationException( new ValidationError("Session de usuario no valida. Se debe logar en el sistema."));
		}
		
	}


	@Override
	public String getNif() {
		return nif;
	}


	public void setNif(String nif) {
		this.nif = nif;
	}
	
	
	@Override
	public boolean isDriver(){
		return driver;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ticket [");
		if (nif != null) {
			builder.append("nif=");
			builder.append(nif);
			builder.append(", ");
		}
		if (email != null) {
			builder.append("email=");
			builder.append(email);
			builder.append(", ");
		}
		if (password != null) {
			builder.append("password=");
			builder.append(password);
			builder.append(", ");
		}
		builder.append("isValid=");
		builder.append(isValid);
		builder.append(", ");
		if (expirationDate != null) {
			builder.append("expirationDate=");
			builder.append(expirationDate);
			builder.append(", ");
		}
		builder.append("driver=");
		builder.append(driver);
		builder.append("]");
		return builder.toString();
	}

	
	
	
	
}
