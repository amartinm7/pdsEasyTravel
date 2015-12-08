package edu.uoc.pds.pra4.presentation.tripadministration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.uoc.pds.pra4.business.tripadministration.TripAdministrationFacadeLocal;
import edu.uoc.pds.pra4.business.useradministration.ITicket;
import edu.uoc.pds.pra4.exception.EasyTravelException;
import edu.uoc.pds.pra4.integration.TripJPA;
import edu.uoc.pds.pra4.presentation.utils.FacadeServicesEJB;

/**
 * Managed Bean FindMyTripsMBean
 */
@ManagedBean(name = MBeanNames.FIND_MY_TRIPS_MBEAN )
@SessionScoped
public class FindMyTripsMBean implements Serializable, ITripAdministration{

	private static final long serialVersionUID = 1L;
	
	private Collection<TripJPA> listTripJPA;
	
	private boolean isThereRows;

	private ITicket ticket;
	
    @ManagedProperty( value = "#{facadeServicesEJB}" )
    private FacadeServicesEJB facadeServicesEJB;

	
	public FindMyTripsMBean() throws Exception {
		super();
		System.out.println(">>>init FindMyTripsMBean...");
		FacesContext context = FacesContext.getCurrentInstance();
		ticket = (ITicket) context.getExternalContext().getSessionMap().get("ticket");		
		resetModel();

	}
	


	public Collection<TripJPA> getListTripJPA() {
		
		try {
			
			listTripJPA = getTripAdministrationFacade().listTrips (ticket);
			isThereRows = (listTripJPA.size() > 0 );
			
		} catch (EasyTravelException e) {
			System.err.println( e.getMessage() );
			FacesContext.getCurrentInstance().addMessage("errors", new FacesMessage( e.getMessage() ) );
			return null;
		} catch (Exception e) {
			return null;
		}
		
		
		return listTripJPA;
	}



	public ITicket getTicket() {
		return ticket;
	}

	public void setTicket(ITicket ticket) {
		this.ticket = ticket;
	}


	public FacadeServicesEJB getFacadeServicesEJB() {
		return facadeServicesEJB;
	}

	public void setFacadeServicesEJB(FacadeServicesEJB facadeServicesEJB) {
		this.facadeServicesEJB = facadeServicesEJB;
	}
	

	
	private void resetModel(){
		listTripJPA = new ArrayList<TripJPA>();
		isThereRows = false;
	}
	
	public boolean getIsThereRows() {
		return isThereRows;
	}



	@Override
	public TripAdministrationFacadeLocal getTripAdministrationFacade() throws Exception {
		return facadeServicesEJB.getTripAdministrationFacade();
	}
	
	
}
