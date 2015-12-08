package edu.uoc.pds.pra4.business.tripadministration;

import java.util.Collection;

import javax.ejb.Local;

import edu.uoc.pds.pra4.business.useradministration.ITicket;
import edu.uoc.pds.pra4.exception.EasyTravelException;
import edu.uoc.pds.pra4.integration.DriverJPA;
import edu.uoc.pds.pra4.integration.PassengerJPA;
import edu.uoc.pds.pra4.integration.TripJPA;



/**
 * Session EJB Local Interfaces
 */
@Local
public interface TripAdministrationFacadeLocal {
	 /**
	   * Localy invoked method.
	   */
	  public java.util.Collection<DriverJPA> listAllDrivers( ITicket ticket ) throws EasyTravelException; 
	  public Collection<TripJPA> listTripsByDriver( final ITicket ticket) throws EasyTravelException;
	  public Collection<TripJPA> listTrips( final ITicket ticket) throws EasyTravelException;
	  public Collection<PassengerJPA> listPassengersByTrip( final ITicket ticket, final TripJPA trip ) throws EasyTravelException;
	  public PassengerJPA showPassenger( final ITicket ticket ) throws EasyTravelException;
	  public TripJPA showTrip(  final ITicket ticket , final TripJPA trip ) throws EasyTravelException;
	  public void addTrip( final ITicket ticket, final TripJPA trip ) throws EasyTravelException;
	  public void updateTrip( final ITicket ticket, final TripJPA trip ) throws EasyTravelException ;
}