package edu.uoc.pds.pra4.business.tripadministration;

import java.util.List;

import edu.uoc.pds.pra4.exception.EasyTravelException;
import edu.uoc.pds.pra4.exception.ValidationError;

public class TripAdministrationException extends EasyTravelException {

	/**
	 * serial ID
	 */
	private static final long serialVersionUID = 4569662218739691156L;
	

	
	public TripAdministrationException(  ){
		super();
	}
	
	public TripAdministrationException( ValidationError error ){
		super(error);
	}
	
	public TripAdministrationException( final List<ValidationError> errors ) {
		super(errors);
	}

}
