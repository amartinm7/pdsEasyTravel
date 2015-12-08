package edu.uoc.pds.pra4.business.tripadministration;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import edu.uoc.pds.pra4.business.useradministration.ITicket;
import edu.uoc.pds.pra4.business.useradministration.TicketStore;
import edu.uoc.pds.pra4.exception.EasyTravelException;
import edu.uoc.pds.pra4.exception.ValidationError;
import edu.uoc.pds.pra4.integration.DriverJPA;
import edu.uoc.pds.pra4.integration.PassengerJPA;
import edu.uoc.pds.pra4.integration.TripJPA;

/**
 * EJB Session Bean Class of "Practica UOC"
 */
@Stateless
public class TripAdministrationFacadeBean implements TripAdministrationFacadeLocal, TripAdministrationFacadeRemote, Serializable{

	/**
	 * SerialID
	 */
	private static final long serialVersionUID = -1040595550654633672L;
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private TicketStore ticketStore = TicketStore.getTicketStore();
	
	/**
	 * Method that returns Collection of all drivers
	 */
	@Override
	public java.util.Collection<DriverJPA> listAllDrivers( ITicket ticket ) throws EasyTravelException {
		
		ticketStore.validateTicket( ticket );	
		
		final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		
		final CriteriaQuery<DriverJPA> cq = cb.createQuery(DriverJPA.class);
		
		@SuppressWarnings("unused")
		final Root<DriverJPA> driverJPA = cq.from(DriverJPA.class);

		final TypedQuery<DriverJPA> query = entityManager.createQuery(cq);
	
		final List<DriverJPA> results = query.getResultList();
		
		return results;

	}
	
	/**
	 * Method that returns Collection of all trips by driver
	 */
	@Override
	public Collection<TripJPA> listTripsByDriver( ITicket ticket ) throws EasyTravelException {
		
		ticketStore.validateTicket( ticket );	
		
		final DriverJPA driverJPA = entityManager.find( DriverJPA.class, ticket.getNif() );
		
		return driverJPA.getTrips();
	    	  
	}
	
	
	/**
	 * Method that returns Collection of all trips by driver
	 */
	@Override
	public Collection<TripJPA> listTrips( ITicket ticket ) throws EasyTravelException {
		
		ticketStore.validateTicket( ticket );	
		
		if ( ticket.isDriver() ){
			
			final DriverJPA driverJPA = entityManager.find( DriverJPA.class, ticket.getNif() );
			return driverJPA.getTrips();
			
		} else {
			
			final PassengerJPA passengerJPA = entityManager.find( PassengerJPA.class, ticket.getNif() );
			return passengerJPA.getTrips();
			
		}
	    	  
	}
	
	
	/**
	 * Method that returns Collection of all passengers by trip
	*/
	@Override
	public Collection<PassengerJPA> listPassengersByTrip ( final ITicket ticket, final TripJPA trip ) throws EasyTravelException {
			
		ticketStore.validateTicket( ticket );	
		
		final TripJPA tripPersisted = entityManager.find( TripJPA.class, trip.getId() );
		
		return tripPersisted.getPassengers();
	    	  
	}
	
	/**
	 * Method that returns instance of the class passenger
	 */
	@Override
	public PassengerJPA showPassenger ( final ITicket ticket ) throws EasyTravelException {

		ticketStore.validateTicket( ticket );	
		
		if ( !ticket.isDriver() ) {
			
			final PassengerJPA passengerJPA = entityManager.find( PassengerJPA.class, ticket.getNif() );
			return passengerJPA;
			
		} else {
			
			throw new TripAdministrationException (new ValidationError("El usuario solicitado no es de tipo pasajero."));
			
		}

	}
	
	/**
	 * Method that returns instance of the class trip
	 */
	@Override
	public TripJPA showTrip ( final ITicket ticket, final TripJPA tripJPA ) throws EasyTravelException {

		ticketStore.validateTicket( ticket );	
		
		if ( tripJPA == null || tripJPA.getId() == null ){
			throw new TripAdministrationException (new ValidationError("No se puede recuperar un viaje sin su identificador."));
		}
		
		final TripJPA tripJPAPersisted = entityManager.find (TripJPA.class, tripJPA.getId() );
		
		return tripJPAPersisted;
		
	}
	
	/**
	 * Method that saves instance of the class trip
	 */
	@Override
	public void addTrip ( final ITicket ticket, final TripJPA tripJPA ) throws EasyTravelException {
		
		ticketStore.validateTicket( ticket );	
		
		tripJPA.validateCaseInsert();
		
		if ( ticket.isDriver() ){
			
			DriverJPA driverJPA = entityManager.find( DriverJPA.class, ticket.getNif() );
			driverJPA.add (tripJPA);
			entityManager.persist(tripJPA);
			
		} else {
			throw new TripAdministrationException (new ValidationError("El usuario es tipo pasajero. No puede dar de alta un viaje."));
		}

				
	}
	
	/**
	 * Method that update instance of the class trip
	 */
	@Override
	public void updateTrip( final ITicket ticket, final TripJPA tripJPA) throws EasyTravelException {

		ticketStore.validateTicket( ticket );	
		
		tripJPA.validateCaseUpdate();
		
		if ( ticket.isDriver() ){
			
			TripJPA tripJPAStored = entityManager.find( TripJPA.class, tripJPA.getId() );
			tripJPAStored.update(tripJPA);
			entityManager.merge(tripJPAStored);
			
		} else {
			throw new TripAdministrationException (new ValidationError("El usuario es tipo pasajero. No puede modificar un viaje."));
		}
		
	}
}
