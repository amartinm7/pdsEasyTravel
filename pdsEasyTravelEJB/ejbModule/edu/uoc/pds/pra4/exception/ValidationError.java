package edu.uoc.pds.pra4.exception;

import java.io.Serializable;

public class ValidationError implements Serializable{
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -5849410539345728523L;
	
	private static final String MESSAGE = "El campo %s no es valido";
	
	private String messageError;

	
	public ValidationError(final String messageError) {
		super();
		this.messageError = messageError;
	}

	public String getMessageError() {
		return messageError;
	}

	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}
	
	public String getFormatedMessageError(){
		return String.format( MESSAGE, messageError );
	}
	
	
}
