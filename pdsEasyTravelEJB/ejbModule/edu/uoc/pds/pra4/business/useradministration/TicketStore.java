package edu.uoc.pds.pra4.business.useradministration;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import edu.uoc.pds.pra4.exception.EasyTravelException;
import edu.uoc.pds.pra4.exception.ValidationError;

/**
 * TicketStore
 * @author amm
 */
public final class TicketStore implements Serializable {
	
	/**
	 * serial id
	 */
	private static final long serialVersionUID = -4175636593763385294L;

	private final Map<String, ITicket> storeMap;
	
	private static final TicketStore TICKET_STORE = new TicketStore();
	
	private TicketStore(){
		super();
		storeMap = new HashMap<String, ITicket>();
	}
	
	public static TicketStore getTicketStore(){
		
		return TICKET_STORE;
	}
	
	public void addTicket( final ITicket ticket ){
		
		TICKET_STORE.storeMap.put( ticket.getEmail(), ticket );
		
	}
	
	public void removeTicket( final ITicket ticket ){
		
		TICKET_STORE.storeMap.remove( ticket.getEmail() );
		
	}
	
	private boolean isTicketStored( final ITicket ticket ){
		
		boolean isStored = ( TICKET_STORE.storeMap.get( ticket.getEmail() ) != null );
		return isStored;
	}
	
	/**
	 * Indica si el cliente está autenticado en el sistema
	 * @throws EasyTravelException
	 */
	public void validateTicket( final ITicket ticket ) throws EasyTravelException {
		
		if (ticket == null) {
			System.err.println( String.format (  "%s : %s ", this.getClass(), "Session de usuario no valida. El usuario no se ha logado y no está guardada su session en el sistema.") );
			throw new UserAdministrationException( new ValidationError("Session de usuario no valida. El usuario no se ha logado y no está guardada su session en el sistema."));
		}
		
		ticket.validateTicket();
		
		if ( ! isTicketStored ( ticket ) ) {
			System.err.println( String.format (  "%s : %s ", this.getClass(), "Session de usuario no valida. El usuario no se ha logado y no está guardada su session en el sistema.") );
			throw new UserAdministrationException( new ValidationError("Session de usuario no valida. El usuario no se ha logado y no está guardada su session en el sistema."));
		}
		
	}
	
}
