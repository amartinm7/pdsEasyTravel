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
import edu.uoc.pds.pra4.integration.DriverJPA;
import edu.uoc.pds.pra4.presentation.utils.FacadeServicesEJB;

/**
 * Managed Bean ListDriversMBean
 */
@ManagedBean(name = MBeanNames.LIST_DRIVERS_MBEAN )
@SessionScoped
public class ListDriversMBean implements Serializable, ITripAdministration{
	
	private static final long serialVersionUID = 1L;
	
	
	private Collection<DriverJPA> listDriversJPA;
	
	private boolean isThereRows;

	private ITicket ticket;
	
    @ManagedProperty( value = "#{facadeServicesEJB}" )
    private FacadeServicesEJB facadeServicesEJB;

	
	public ListDriversMBean() throws Exception {
		super();
		System.out.println(">>>init ListDriversMBean...");
		FacesContext context = FacesContext.getCurrentInstance();
		ticket = (ITicket) context.getExternalContext().getSessionMap().get("ticket");		
		resetModel();

	}
	


	public Collection<DriverJPA> getListTripJPA() {
		
		try {
			
			listDriversJPA = getTripAdministrationFacade().listAllDrivers( ticket );
			isThereRows = (listDriversJPA.size() > 0 );
			
		} catch (EasyTravelException e) {
			System.err.println( e.getMessage() );
			FacesContext.getCurrentInstance().addMessage("errors", new FacesMessage( e.getMessage() ) );
			return null;
		} catch (Exception e) {
			return null;
		}
		
		
		return listDriversJPA;
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
		listDriversJPA = new ArrayList<DriverJPA>();
		isThereRows = false;
	}
	
	public boolean getIsThereRows() {
		return isThereRows;
	}



	@Override
	public TripAdministrationFacadeLocal getTripAdministrationFacade() throws Exception {
		return facadeServicesEJB.getTripAdministrationFacade();
	}



	public Collection<DriverJPA> getListDriversJPA() {
		return listDriversJPA;
	}



	public void setListDriversJPA(Collection<DriverJPA> listDriversJPA) {
		this.listDriversJPA = listDriversJPA;
	}



	public void setThereRows(boolean isThereRows) {
		this.isThereRows = isThereRows;
	}
	
	
 
}
