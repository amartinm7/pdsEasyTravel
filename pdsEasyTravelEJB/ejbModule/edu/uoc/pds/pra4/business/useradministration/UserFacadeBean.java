package edu.uoc.pds.pra4.business.useradministration;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import edu.uoc.pds.pra4.exception.EasyTravelException;
import edu.uoc.pds.pra4.exception.ValidationError;
import edu.uoc.pds.pra4.integration.CarJPA;
import edu.uoc.pds.pra4.integration.DriverJPA;
import edu.uoc.pds.pra4.integration.IUserJPA;
import edu.uoc.pds.pra4.integration.PassengerJPA;
import edu.uoc.pds.pra4.integration.UserJPA;

/**
 * UserFacadeBean
 * @author amm
 */
@Stateless
public class UserFacadeBean implements UserFacadeLocal, UserFacadeRemote, Serializable{

	
	/**
	 * SerialID
	 */
	private static final long serialVersionUID = 4975767491245847174L;

	@PersistenceContext
	private EntityManager entityManager;
	
	private TicketStore ticketStore = TicketStore.getTicketStore();
	

	@Override
	public ITicket login( final String email, final String password ) throws EasyTravelException {
		
		//http://docs.oracle.com/javaee/6/tutorial/doc/gjrij.html
		//http://www.objectdb.com/java/jpa/query/criteria
		
	    Ticket ticket = Ticket.getInvalidTicket(email, password);
		
		final List<UserJPA> results = getUserJPAByEmail(email, password);
		
		System.out.println(results);
		
		if ( results == null || results.size() == 0 ){
			
			throw new UserAdministrationException( new ValidationError("Usuario incorrecto. Introduzca los datos de nuevo."));
		} ;
		
		ticket = new Ticket( results.get(0) );//crea un ticket valido
		ticketStore.addTicket(ticket);
		
		return ticket;
	}

	/**
	 * getUserJPAByEmail
	 * @param email
	 * @param password
	 * @return List<UserJPA>
	 */
	private List<UserJPA> getUserJPAByEmail ( final String email, final String password  ) {
		
		final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		
		final CriteriaQuery<UserJPA> cq = cb.createQuery(UserJPA.class);
		
		final Root<UserJPA> userJPA = cq.from(UserJPA.class);

		final ParameterExpression<String> emailPE = cb.parameter(String.class);
		
		final ParameterExpression<String> passwordPE = cb.parameter(String.class);
		
		if ( password != null ) {
			cq.where(cb.and( cb.equal( userJPA.get("email"), emailPE ), cb.equal( userJPA.get("password"), passwordPE ) )  );	
		} else {
			cq.where( cb.equal( userJPA.get("email"), emailPE )  );	
		}
		
		final TypedQuery<UserJPA> query = entityManager.createQuery(cq);
		
		query.setParameter(emailPE, email);
		
		if ( password != null ) {
			query.setParameter(passwordPE, password);
		}
		
		final List<UserJPA> results = query.getResultList();
		
		return results;
	}
	
	
	@Override
	public void logout( final ITicket ticket ) {
		
		ticketStore.removeTicket( ticket );
	}
	 
	@Override
	public void registerDriver ( final ITicket ticket, final DriverJPA driverJPA ) throws EasyTravelException {
		
		driverJPA.validateCaseInsert();
		
		if ( entityManager.find(DriverJPA.class, driverJPA.getNif()) != null ){
			throw new UserAdministrationException( new ValidationError("No ha sido posible persistir al conductor. Ya existe otro conductor con el mismo nif."));
		}
		
		final List<UserJPA> results = getUserJPAByEmail( driverJPA.getEmail(), null);
		
		if ( results !=null && results.size() > 0  ) {
			throw new UserAdministrationException( new ValidationError("No ha sido posible persistir al conductor. Ya existe otro usuario con el mismo email."));
		}
		
		entityManager.persist( driverJPA );
		
	}
	
	@Override
	public void updatePersonalData ( final ITicket ticket, final IUserJPA userJPA ) throws EasyTravelException {
		
		ticketStore.validateTicket(ticket);

		userJPA.validateCaseUpdate();
		
		if (userJPA.getUserType().equals( PassengerJPA.USER_TYPE )) {
			entityManager.merge( new PassengerJPA(userJPA) );
		} else {
			entityManager.merge( new DriverJPA(userJPA) );
		}
		
	}		
	
	@Override
	public void registerPassenger ( final ITicket ticket, final PassengerJPA passengerJPA ) throws EasyTravelException {
		
		passengerJPA.validateCaseInsert();
		
		if ( entityManager.find(PassengerJPA.class, passengerJPA.getNif()) != null ){
			throw new UserAdministrationException( new ValidationError("No ha sido posible persistir al pasajero. Ya existe otro pasajero con el mismo nif."));
		}
		
		final List<UserJPA> results = getUserJPAByEmail( passengerJPA.getEmail(), null);
		
		if ( results !=null && results.size() > 0  ) {
			throw new UserAdministrationException( new ValidationError("No ha sido posible persistir al pasajero. Ya existe otro usuario con el mismo email."));
		}
		
		entityManager.persist( passengerJPA );
		
	}
	
	@Override
	public void addCar ( final ITicket ticket, final CarJPA carJPA ) throws EasyTravelException {
		
		ticketStore.validateTicket(ticket);
		
		final List<UserJPA> results = getUserJPAByEmail( ticket.getEmail(), null);
		
		if ( results == null || results.size() == 0  ) {
			throw new UserAdministrationException( new ValidationError("No existe el dueño del coche a persistir."));
		}
		
		final UserJPA userJPA = results.get(0);
		
		final DriverJPA driverJPA = entityManager.find( DriverJPA.class, userJPA.getNif() );
		
		if ( driverJPA == null ){
			throw new UserAdministrationException( new ValidationError("No se ha asignado un conductor valido."));
		}
		
		carJPA.setDriverJPA ( driverJPA );
		carJPA.validateCaseInsert();
		
		entityManager.persist( carJPA );
		
	}
	
	@Override
	public List<CarJPA> listAllCars ( final ITicket ticket ) throws EasyTravelException {
		
		ticketStore.validateTicket(ticket);
		
		final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		
		final CriteriaQuery<CarJPA> cq = cb.createQuery(CarJPA.class);
		
		final Root<CarJPA> carJPA = cq.from(CarJPA.class);
		
		final Join<CarJPA, DriverJPA> owner = carJPA.join("driverJPA");

		final ParameterExpression<String> emailPE = cb.parameter(String.class);
		

		cq.where( cb.equal( owner.get("email"), emailPE )  );	

		
		final TypedQuery<CarJPA> query = entityManager.createQuery(cq);
		
		query.setParameter( emailPE, ticket.getEmail() );
		
		final List<CarJPA> results = query.getResultList();
		
		return results;
		
		
	}
	
	@Override
	public void deleteCar ( final ITicket ticket, CarJPA carJPA ) throws EasyTravelException {
		
		ticketStore.validateTicket(ticket);
		
		carJPA.validateCaseDelete();
		carJPA = entityManager.find( carJPA.getClass(), carJPA.getCarRegistrationId() );
		entityManager.remove( carJPA );
		
	}
	
	@Override
	public UserJPA getUserJPA ( final ITicket ticket ) throws EasyTravelException{
			
		ticketStore.validateTicket(ticket);
		
		return entityManager.find(UserJPA.class, ticket.getNif());
		
	}

	
	
}
