package edu.uoc.pds.pra4.presentation.tripadministration;

import java.io.Serializable;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import edu.uoc.pds.pra4.business.tripadministration.TripAdministrationFacadeLocal;
import edu.uoc.pds.pra4.business.useradministration.ITicket;
import edu.uoc.pds.pra4.business.useradministration.Ticket;
import edu.uoc.pds.pra4.presentation.utils.FacadeServicesEJB;

@SuppressWarnings("serial")
public abstract class AbstractTripAdministration implements Serializable, ITripAdministration {

    @ManagedProperty( value = "#{facadeServicesEJB}" )
    private FacadeServicesEJB facadeServicesEJB;
    
    
	@Override
	public TripAdministrationFacadeLocal getTripAdministrationFacade() throws Exception {
		return facadeServicesEJB.getTripAdministrationFacade();
	}
    
	public FacadeServicesEJB getFacadeServicesEJB() {
		return facadeServicesEJB;
	}

	public void setFacadeServicesEJB(FacadeServicesEJB facadeServicesEJB) {
		this.facadeServicesEJB = facadeServicesEJB;
	}
	
	protected ITicket getTicket() {
		FacesContext context = FacesContext.getCurrentInstance();
		ITicket ticket = (ITicket) context.getExternalContext().getSessionMap().get("ticket");		
		
		if (ticket == null) {
			ticket = Ticket.getInvalidTicket("", "");//init ticket, evict nullpointer
		}
		
		return ticket;
	}
	
}
