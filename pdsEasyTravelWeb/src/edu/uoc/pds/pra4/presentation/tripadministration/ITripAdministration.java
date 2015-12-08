package edu.uoc.pds.pra4.presentation.tripadministration;

import edu.uoc.pds.pra4.business.tripadministration.TripAdministrationFacadeLocal;

public interface ITripAdministration {

	public TripAdministrationFacadeLocal getTripAdministrationFacade() throws Exception;
}
