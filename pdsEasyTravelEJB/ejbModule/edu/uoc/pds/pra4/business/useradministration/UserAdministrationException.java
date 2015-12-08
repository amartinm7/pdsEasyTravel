package edu.uoc.pds.pra4.business.useradministration;

import java.util.List;

import edu.uoc.pds.pra4.exception.EasyTravelException;
import edu.uoc.pds.pra4.exception.ValidationError;

public class UserAdministrationException extends EasyTravelException {

	/**
	 * serial ID
	 */
	private static final long serialVersionUID = 4569662218739691156L;
	

	public UserAdministrationException(  ){
		super();
	}
	
	public UserAdministrationException( ValidationError error ){
		super(error);
	}
	
	public UserAdministrationException( final List<ValidationError> errors ) {
		super(errors);
	}

}
