package edu.uoc.pds.pra4.integration;

import edu.uoc.pds.pra4.exception.EasyTravelException;

public interface IValidable {

	public void validateCaseInsert() throws EasyTravelException;
	
	public void validateCaseUpdate() throws EasyTravelException;
	
}
