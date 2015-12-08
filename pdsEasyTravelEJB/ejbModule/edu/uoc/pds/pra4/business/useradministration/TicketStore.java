package edu.uoc.pds.pra4.business.useradministration;

import java.util.HashMap;
import java.util.Map;

import edu.uoc.pds.pra4.exception.EasyTravelException;
import edu.uoc.pds.pra4.exception.ValidationError;

/**
 * TicketStore
 * @author amm
 */
public class TicketStore {
	
	private final Map<String, ITicket> storeMap;
	
	private static final TicketStore ticketStore = new TicketStore();
	
	private TicketStore(){
		super();
		storeMap = new HashMap<String, ITicket>();
	}
	
	public static TicketStore getTicketStore(){
		
		return ticketStore;
	}
	
	public void addTicket( final ITicket ticket ){
		
		ticketStore.storeMap.put( ticket.getEmail(), ticket );
		
	}
	
	public void removeTicket( final ITicket ticket ){
		
		ticketStore.storeMap.remove( ticket.getEmail() );
		
	}
	
	private boolean isTicketStored( final ITicket ticket ){
		
		boolean isStored = ( ticketStore.storeMap.get( ticket.getEmail() ) != null );
		return isStored;
	}
	
	/**
	 * Indica si el cliente está autenticado en el sistema
	 * @throws EasyTravelException
	 */
	public void validateTicket( final ITicket ticket ) throws EasyTravelException {
		
		if (ticket == null) {
			throw new UserAdministrationException( new ValidationError("Session de usuario no valida. El usuario no se ha logado y no está guardada su session en el sistema."));
		}
		
		ticket.validateTicket();
		
		if ( ! isTicketStored ( ticket ) ) {
			throw new UserAdministrationException( new ValidationError("Session de usuario no valida. El usuario no se ha logado y no está guardada su session en el sistema."));
		}
		
	}
	
}
