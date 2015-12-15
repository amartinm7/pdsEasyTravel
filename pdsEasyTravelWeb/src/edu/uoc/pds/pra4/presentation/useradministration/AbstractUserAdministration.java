package edu.uoc.pds.pra4.presentation.useradministration;

import java.io.Serializable;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import edu.uoc.pds.pra4.business.useradministration.ITicket;
import edu.uoc.pds.pra4.business.useradministration.Ticket;
import edu.uoc.pds.pra4.business.useradministration.UserFacadeLocal;
import edu.uoc.pds.pra4.presentation.utils.FacadeServicesEJB;

@SuppressWarnings("serial")
public abstract class AbstractUserAdministration implements Serializable, IUserAdministration{

    @ManagedProperty( value = "#{facadeServicesEJB}" )
    private FacadeServicesEJB facadeServicesEJB;
	
	@Override
	public UserFacadeLocal getUserFacade() throws Exception {
		
		return facadeServicesEJB.getUserFacade();

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
