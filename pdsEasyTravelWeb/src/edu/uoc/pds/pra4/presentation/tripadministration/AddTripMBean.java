package edu.uoc.pds.pra4.presentation.tripadministration;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.uoc.pds.pra4.business.tripadministration.TripAdministrationFacadeLocal;
import edu.uoc.pds.pra4.business.useradministration.ITicket;
import edu.uoc.pds.pra4.exception.EasyTravelException;
import edu.uoc.pds.pra4.integration.TripJPA;
import edu.uoc.pds.pra4.presentation.navigation.NavigationMBean;
import edu.uoc.pds.pra4.presentation.utils.FacadeServicesEJB;

/**
* Managed Bean AddTripMBean
*/
@ManagedBean(name = MBeanNames.ADD_TRIP_MBEAN )
@SessionScoped
public class AddTripMBean implements Serializable, ITripAdministration{
	
	private static final long serialVersionUID = 1L;
	
	private TripJPA tripJPA; 

	private ITicket ticket;

    @ManagedProperty( value = "#{facadeServicesEJB}" )
    private FacadeServicesEJB facadeServicesEJB;
	
	
	public AddTripMBean() throws Exception {
		super();
		System.out.println(">>>init AddTripMBean...");
		FacesContext context = FacesContext.getCurrentInstance();
		ticket = (ITicket) context.getExternalContext().getSessionMap().get("ticket");		
		resetModel();

	}
	
	public String addTrip()  {
		System.out.println(">>>addTrip...");
		
		try {
			
			getTripAdministrationFacade().addTrip( ticket, tripJPA );
			resetModel();//clean trip
			
		} catch (EasyTravelException e) {
			System.err.println( e.getMessage() );
			FacesContext.getCurrentInstance().addMessage("errors", new FacesMessage( e.getMessage() ) );
			return null;
		} catch (Exception e) {
			return null;
		}
		
		return NavigationMBean.toFindMyTripsView();
	}


	public ITicket getTicket() {
		return ticket;
	}

	public void setTicket(ITicket ticket) {
		this.ticket = ticket;
	}

	public TripJPA getTripJPA() {
		return tripJPA;
	}

	public void setTripJPA(TripJPA tripJPA) {
		this.tripJPA = tripJPA;
	}

	public FacadeServicesEJB getFacadeServicesEJB() {
		return facadeServicesEJB;
	}

	public void setFacadeServicesEJB(FacadeServicesEJB facadeServicesEJB) {
		this.facadeServicesEJB = facadeServicesEJB;
	}
	

	
	private void resetModel(){
		tripJPA = new TripJPA();
	}



	@Override
	public TripAdministrationFacadeLocal getTripAdministrationFacade() throws Exception {
		return facadeServicesEJB.getTripAdministrationFacade();
	}



}
