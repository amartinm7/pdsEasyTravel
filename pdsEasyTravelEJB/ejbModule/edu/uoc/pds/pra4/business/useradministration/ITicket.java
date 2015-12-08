package edu.uoc.pds.pra4.business.useradministration;

import java.io.Serializable;
import java.util.Date;

import edu.uoc.pds.pra4.exception.EasyTravelException;

/**
 * ITicket
 * @author amm
 */
public interface ITicket extends Serializable{

	public String getId();

	public String getEmail();

	public String getPassword() ;

	public boolean isValid();

	public Date getExpirationDate();
	
	/**
	 * Indica si el cliente está autenticado en el sistema
	 * @throws EasyTravelException
	 */
	public void validateTicket() throws EasyTravelException;

	public String getNif();
	
	public boolean isDriver();
	
	
}
