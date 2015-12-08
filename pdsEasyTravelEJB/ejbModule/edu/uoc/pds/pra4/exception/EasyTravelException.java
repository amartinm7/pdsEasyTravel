package edu.uoc.pds.pra4.exception;

import java.util.ArrayList;
import java.util.List;

public abstract class EasyTravelException extends Exception {
	
	/**
	 * serial ID
	 */
	private static final long serialVersionUID = 6086108306470422302L;
	
	private final List<ValidationError> errors;
	

	
	public EasyTravelException(  ){
		super();
		this.errors = new ArrayList<ValidationError>();
	}
	
	
	public EasyTravelException( ValidationError error ){
		super();
		this.errors = new ArrayList<ValidationError>();
		this.errors.add(error);
	}

	public EasyTravelException( final List<ValidationError> errors ){
		super();
		this.errors = errors;
	}
	

	public List<ValidationError> getErrors() {
		return errors;
	}

	@Override
	public String getMessage (){
		
		StringBuilder sb = new StringBuilder();
		for (ValidationError validationError : errors) {
			sb.append(validationError.getMessageError()).append(". ");
		}
		return sb.toString();
	}
	
	
}
